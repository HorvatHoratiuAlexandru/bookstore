package com.horvat.bookstore.configs.security;


public interface TokenAuthenticateService {
    public JwtAuthentication verifyToken(JwtAuthentication token);
}
