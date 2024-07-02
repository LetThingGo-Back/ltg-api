package com.letthinggo.ltgapi.global.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.letthinggo.ltgapi.global.response.ApiCommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_FORBIDDEN); //403
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(objectMapper.writeValueAsString(ApiCommonResponse.createErrorWithCode(ErrorCode.USER_ACCESS_DENIED)));
    }

}
