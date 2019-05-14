package com.epam.mentoring.security;

import io.jsonwebtoken.SignatureAlgorithm;

public interface SecurityDefinition {
    String AUTH_LOGIN_URL = "/user/login";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    String JWT_SECRET = "2s5v8y/B?E(H+KbPeShVmYq3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgU";
    byte[] SIGNED_KEY = JWT_SECRET.getBytes();

    // JWT token defaults
    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

    String TOKEN_ISS = "chat-server";
    String TOKEN_TYP = "JWT";

    SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
}
