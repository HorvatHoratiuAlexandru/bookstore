package com.horvat.bookstore.configs.security;

import com.horvat.bookstore.appUser.dtos.requests.LogIn;
import com.horvat.bookstore.appUser.dtos.responses.LoggedIn;

public interface JwtSignIn {

    public LoggedIn signInAndReturnTokens(LogIn credentials);
}
