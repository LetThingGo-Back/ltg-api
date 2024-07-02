package com.letthinggo.ltgapi.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letthinggo.ltgapi.domain.user.data.dto.UserDto;
import com.letthinggo.ltgapi.global.exception.ErrorCode;
import com.letthinggo.ltgapi.global.response.ApiCommonResponse;
import com.letthinggo.ltgapi.global.social.dto.CustomOAuth2User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader("Authorization");
        // accessToken이 없다면
        if(accessToken == null || !accessToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer 제거
        String originToken = accessToken.substring(7);

        // Token 만료 확인
        try {
            if(jwtUtil.isExpired(originToken)) {
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().write(objectMapper.writeValueAsString(ApiCommonResponse.createErrorWithCode(ErrorCode.ACCESS_TOKEN_EXPIRED)));
                return;
            }
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(objectMapper.writeValueAsString(ApiCommonResponse.createErrorWithCode(ErrorCode.ACCESS_TOKEN_EXPIRED)));
            return;
        }

        String category = jwtUtil.getCatgory(originToken);

        if(!category.equals("access")) {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(objectMapper.writeValueAsString(ApiCommonResponse.createErrorWithCode(ErrorCode.INVAILD_ACCESS_TOKEN)));
            return;
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(jwtUtil.getUserId(originToken));
        userDto.setRole(jwtUtil.getRole(originToken));

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}