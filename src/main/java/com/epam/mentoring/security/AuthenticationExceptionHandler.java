package com.epam.mentoring.security;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        ResponseStatusWriter.writeStatusResponse(httpServletResponse, ServiceStatusResponseDto.builder()
                .code(401)
                .message("Authentication failed.")
                .build());
    }
}
