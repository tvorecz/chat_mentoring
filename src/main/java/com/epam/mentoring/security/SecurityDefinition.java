package com.epam.mentoring.security;

public interface SecurityDefinition {
    String AUTH_LOGIN_URL = "/secure/login";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    String JWT_SECRET = "2s5v8y/B?E(H+KbPeShVmYq3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgU";

    // JWT token defaults
    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "secure-api";
    String TOKEN_AUDIENCE = "secure-app";
}
