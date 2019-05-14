package com.epam.mentoring.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class IdUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private int principalId;

    public IdUsernamePasswordAuthenticationToken(Object principal, Object credentials, int principalId) {
        super(principal, credentials);
        this.principalId = principalId;
    }

    public IdUsernamePasswordAuthenticationToken(Object principal,
                                                 Object credentials,
                                                 Collection<? extends GrantedAuthority> authorities, int principalId) {
        super(principal, credentials, authorities);
        this.principalId = principalId;
    }

    public int getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(int principalId) {
        this.principalId = principalId;
    }
}
