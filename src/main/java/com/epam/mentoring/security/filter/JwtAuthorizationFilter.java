package com.epam.mentoring.security.filter;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.StatusResponseDto;
import com.epam.mentoring.security.IdUsernamePasswordAuthenticationToken;
import com.epam.mentoring.security.JwtTokenHeaderBuilder;
import com.epam.mentoring.security.ResponseStatusWriter;
import com.epam.mentoring.security.SecurityDefinition;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter implements SecurityDefinition {
    private JwtTokenHeaderBuilder jwtTokenHeaderBuilder;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtTokenHeaderBuilder jwtTokenHeaderBuilder) {
        super(authenticationManager);
        this.jwtTokenHeaderBuilder = jwtTokenHeaderBuilder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
                                                                                                                 IOException,
                                                                                                                 ServletException {
        String header = request.getHeader(TOKEN_HEADER);

        if (StringUtils.isEmpty(header) || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);

            return;
        }

        IdUsernamePasswordAuthenticationToken authentication =
                null;

        try {
            authentication = jwtTokenHeaderBuilder.createTokenForAuthorization(header);

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            request.setAttribute("userId", authentication.getPrincipalId());

            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            writeExceptionResponse(response, 401, "Token are expired.");
        } catch (UnsupportedJwtException e) {
            writeExceptionResponse(response, 401, "Token are invalid.");
        } catch (MalformedJwtException e) {
            writeExceptionResponse(response, 401, "Token are incorrect.");
        } catch (SignatureException e) {
            writeExceptionResponse(response, 401, "Signature are incorrect.");
        } catch (IllegalArgumentException e) {
            writeExceptionResponse(response, 401, "Incorrect information.");
        }
    }

    private void writeExceptionResponse(HttpServletResponse response, int code, String message) throws IOException {
        ResponseStatusWriter.writeStatusResponse(response, StatusResponseDto.builder()
                .status(ServiceStatusResponseDto.builder()
                                .code(code)
                                .message(message)
                                .build())
                .build());
    }
}
