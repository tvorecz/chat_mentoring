package com.epam.mentoring.security.filter;

import com.epam.mentoring.dto.LoginResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserAuthenticateRequestDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.security.JwtTokenHeaderBuilder;
import com.epam.mentoring.security.ResponseStatusWriter;
import com.epam.mentoring.security.SecurityDefinition;
import com.epam.mentoring.service.status.StatusResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements SecurityDefinition {
    private AuthenticationManager authenticationManager;
    private JwtTokenHeaderBuilder jwtTokenHeaderBuilder;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtTokenHeaderBuilder jwtTokenHeaderBuilder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHeaderBuilder = jwtTokenHeaderBuilder;

        setFilterProcessesUrl(AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
                                                                                                          AuthenticationException {
        try {
            String raw = request.getReader().readLine();
            ObjectMapper objectMapper = new ObjectMapper();

            UserAuthenticateRequestDto requestDto = objectMapper.readValue(raw, UserAuthenticateRequestDto.class);

            String login = requestDto.getLogin();
            String password = requestDto.getPassword();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login, password);

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            return authenticationManager.authenticate(null);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        User user = ((User) authentication.getPrincipal());

        String tokenBody = jwtTokenHeaderBuilder.createTokenHeaderForResponse(user);

        response.addHeader(TOKEN_HEADER, tokenBody);
//        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers",
                           "x-requested-with, x-requested-by, Authorization, Origin, Content-Type");
        response.addHeader("Access-Control-Expose-Headers",
                           "x-requested-with, x-requested-by, Authorization, Origin, Content-Type");
//        response.addHeader("Access-Control-Allow-Credentials", "true");

        ResponseStatusWriter.writeStatusResponse(response,
                                                 LoginResponseDto.builder()
                                                         .user(UserResponseDto.builder()
                                                                       .id(user.getId())
                                                                       .login(user.getLogin())
                                                                       .nickname(user.getNickname())
                                                                       .build())
                                                         .status(ServiceStatusResponseDto.builder()
                                                                         .code(StatusResponse.SUCCESS.getCode())
                                                                         .message("Success.")
                                                                         .build())
                                                         .build()
                , StatusResponse.SUCCESS.getHttpStatus());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
//        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers",
                           "x-requested-with, x-requested-by, Authorization, Origin, Content-Type");
        response.addHeader("Access-Control-Expose-Headers",
                           "x-requested-with, x-requested-by, Authorization, Origin, Content-Type");
//        response.addHeader("Access-Control-Allow-Credentials", "true");

        ResponseStatusWriter.writeStatusResponse(response,
                                                 LoginResponseDto.builder()
                                                         .status(ServiceStatusResponseDto.builder()
                                                                         .code(StatusResponse.UNAUTHORIZED.getCode())
                                                                         .message("Access denied.")
                                                                         .build())
                                                         .build(),
                                                 StatusResponse.UNAUTHORIZED.getHttpStatus());
    }


}
