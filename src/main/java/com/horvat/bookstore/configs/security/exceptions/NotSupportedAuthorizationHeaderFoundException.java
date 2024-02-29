package com.horvat.bookstore.configs.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class NotSupportedAuthorizationHeaderFoundException extends AuthenticationException{

    public NotSupportedAuthorizationHeaderFoundException(String msg) {
        super(msg);
    }

}
