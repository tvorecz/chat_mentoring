package com.epam.mentoring.security.filter;

import com.epam.mentoring.entity.User;
import com.epam.mentoring.security.JwtTokenHeaderBuilder;
import com.epam.mentoring.security.SecurityDefinition;
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

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenHeaderBuilder jwtTokenHeaderBuilder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHeaderBuilder = jwtTokenHeaderBuilder;

        setFilterProcessesUrl(AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
                                                                                                          AuthenticationException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        User user = ((User) authentication.getPrincipal());

        String tokenBody = jwtTokenHeaderBuilder.createTokenHeaderForResponse(user);

        response.addHeader(TOKEN_HEADER, tokenBody);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
