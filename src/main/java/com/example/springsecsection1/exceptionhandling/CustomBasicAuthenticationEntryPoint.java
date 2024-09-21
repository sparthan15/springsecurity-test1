package com.example.springsecsection1.exceptionhandling;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        LocalDateTime currentTimeStamp = LocalDateTime.now();
        response.setHeader("eazybank-error-reason", "Authentication failed!");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendError(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setContentType("application/json;charset=UTF-8");
        String jsonResponse = String.format("{" +
                "    \"timestamp\":\"%s\",\n" +
                "    \"what\":\"%s\",\n" +
                "    \"status\":\"%d\"\n" +
                "}", currentTimeStamp, "YEa yea", HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(jsonResponse);
    }
}
