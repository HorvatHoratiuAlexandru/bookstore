package com.horvat.bookstore.configs.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UnknowAuthenticationException extends AuthenticationException {

    public UnknowAuthenticationException(String msg) {
        super(msg);
    }

}
