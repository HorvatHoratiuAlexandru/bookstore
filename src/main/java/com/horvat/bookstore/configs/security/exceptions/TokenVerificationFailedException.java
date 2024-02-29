package com.horvat.bookstore.configs.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenVerificationFailedException extends AuthenticationException {

    public TokenVerificationFailedException(String msg) {
        super(msg);
    }

}
