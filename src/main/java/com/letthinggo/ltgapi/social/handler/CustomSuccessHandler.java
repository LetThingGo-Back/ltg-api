package com.letthinggo.ltgapi.social.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.letthinggo.ltgapi.data.dto.TokenResponseDto;
import com.letthinggo.ltgapi.data.dto.UserResponseDto;
import com.letthinggo.ltgapi.response.ApiCommonResponse;
import com.letthinggo.ltgapi.service.SocialLoginService;
import com.letthinggo.ltgapi.service.UserService;
import com.letthinggo.ltgapi.social.dto.CustomOAuth2User;
import com.letthinggo.ltgapi.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    private final SocialLoginService socialLoginService;
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
        TokenResponseDto token = null;
        try {
            token = socialLoginService.createJwtToken(oAuth2User);
            UserResponseDto userResponse = userService.findOne(oAuth2User.getUserId());
            response.setHeader("Authorization", "Bearer " +  token.getAccessToken());
            response.addCookie(CookieUtil.createCookie("refreshToken", token.getRefreshToken()));
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(objectMapper.writeValueAsString(ApiCommonResponse.createSuccess(userResponse)));
            response.sendRedirect("https://letthinggo.com");       // TODO: 로그인 성공시 프론트 redirect 경로
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}