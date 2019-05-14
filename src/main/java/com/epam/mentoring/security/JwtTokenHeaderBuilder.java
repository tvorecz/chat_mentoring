package com.epam.mentoring.security;

import com.epam.mentoring.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenHeaderBuilder implements SecurityDefinition {
    public String createTokenHeaderForResponse(User user) {
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        String tokenBody = Jwts.builder().signWith(Keys.hmacShaKeyFor(SIGNED_KEY), SignatureAlgorithm.HS512)
                .setSubject(user.getUsername())
                .setExpiration(Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant()))
                .setHeaderParam("typ", TOKEN_TYP)
                .setIssuer(TOKEN_ISS)
                .claim("rol", roles)
                .claim("userId", Integer.valueOf(user.getId()))
                .compact();

        StringBuilder resultBuilder = new StringBuilder(TOKEN_PREFIX);
        resultBuilder.append(tokenBody);

        return resultBuilder.toString();
    }

    public IdUsernamePasswordAuthenticationToken createTokenForAuthorization(String header) {
        if (!StringUtils.isEmpty(header)) {
            Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(SIGNED_KEY)
                    .parseClaimsJws(header.replace(TOKEN_PREFIX, ""));

            String username = parsedToken
                    .getBody()
                    .getSubject();

            List<GrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                    .get("rol")).stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority))
                    .collect(Collectors.toList());

            Integer userId = parsedToken.getBody().get("userId", Integer.class);

            Date currentExpiration = parsedToken.getBody().getExpiration();

            if (!StringUtils.isEmpty(username) || isExpirationValid(currentExpiration)) {
                return new IdUsernamePasswordAuthenticationToken(username, null, authorities, userId);
            }
        }

        return null;
    }

    private boolean isExpirationValid(Date currentExpiration) {
        LocalDateTime localDateTime = currentExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return localDateTime.isAfter(localDateTime);
    }


}
