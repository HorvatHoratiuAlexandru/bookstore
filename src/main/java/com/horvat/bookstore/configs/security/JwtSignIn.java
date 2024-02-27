package com.horvat.bookstore.configs.security;

import java.util.Map;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.horvat.bookstore.appUser.dtos.requests.LogIn;
import com.horvat.bookstore.appUser.dtos.responses.LoggedIn;

public interface JwtSignIn {
    public LoggedIn signInAndReturnTokens(LogIn credentials);
    public JwtAuthentication verifyToken(JwtAuthentication token);
    public Map<String, ?> getClaimsMap(DecodedJWT decodedJwt);
}
