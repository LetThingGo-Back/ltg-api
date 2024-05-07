package com.letthinggo.ltgapi.handler;


import com.letthinggo.ltgapi.data.dto.CustomOAuth2User;
import com.letthinggo.ltgapi.data.dto.RefreshTokenDto;
import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import com.letthinggo.ltgapi.service.RefreshTokenService;
import com.letthinggo.ltgapi.service.SocialLoginService;
import com.letthinggo.ltgapi.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
        String role = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_USER")) {
                role = authority.getAuthority();
                break;
            }
        }
        String accessToken = jwtUtil.createJwt("access", oAuth2User.getUserId(), role, 86400000L); // 24 hours
        String refreshToken = jwtUtil.createJwt("refresh", oAuth2User.getUserId(), role, 2592000000L); //30 days

        // TODO: 추후에 redis로 변경
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setUserId(oAuth2User.getUserId());
        refreshTokenDto.setSocialCode(oAuth2User.getSocialCode());
        refreshTokenDto.setExternalId(oAuth2User.getExternalId());
        refreshTokenDto.setRefreshToken(refreshToken);
        refreshTokenDto.setExpirationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + 2592000000L), ZoneId.systemDefault()));
        refreshTokenService.createRefreshToken(refreshTokenDto);

        response.setHeader("access", "Bearer " + accessToken);
        response.addCookie(createCookie("refresh", refreshToken));
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect("http://localhost:3000/hello");       // 로그인 성공시 프론트 redirect 경로

    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        /*cookie.setSecure();*/         // https 설정
        /*cookie.setPath("/");*/
        cookie.setHttpOnly(true);

        return cookie;
    }

}