package com.horvat.bookstore.configs.security;


import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication implements Authentication {
    private static final String CONST_UID = "id";
    private static final String CONST_PRINCIPAL = "email";
    private static final String CONST_NAME = "name";
    private static final String CONST_AUTHORITY = "role";

    private final Map<String, ?> claims;
    private final String credential;
    private final JwtTokenStatus status;

    public JwtAuthentication(String credential){
        this.credential = credential;
        this.claims = null;
        this.status = JwtTokenStatus.NA;
    }

    public JwtAuthentication(String credential, JwtTokenStatus status, Map<String, ?> details){
        this.credential = credential;
        this.status = status;
        this.claims = details;
    }

    @Override
    public String getName() {
        if(claims == null) return null;
        return (String) this.claims.get(JwtAuthentication.CONST_NAME);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(claims == null) return null;
        GrantedAuthority authority = () -> (String) claims.get(JwtAuthentication.CONST_AUTHORITY);
        
        return Arrays.asList(authority);
    }

    @Override
    public Object getCredentials() {
        return (String) this.credential;
    }

    @Override
    public Object getDetails() {
        return claims;
    }

    @Override
    public Object getPrincipal() {
        if(claims == null) return null;
        return (String) claims.get(JwtAuthentication.CONST_PRINCIPAL);
    }

    @Override
    public boolean isAuthenticated() {
        if(status == JwtTokenStatus.OK){
            return true;
        } else {
            return false;
        }
    }

    public JwtTokenStatus getStatus(){
        return this.status;
    }

    public Integer getUserId(){
        if(claims == null) return null;
        return (Integer) claims.get(JwtAuthentication.CONST_UID);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Leave me alone");
    }

}
