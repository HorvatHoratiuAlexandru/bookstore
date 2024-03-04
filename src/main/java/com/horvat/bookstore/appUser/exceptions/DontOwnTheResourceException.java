package com.horvat.bookstore.appUser.exceptions;

public class DontOwnTheResourceException extends RuntimeException {
    public DontOwnTheResourceException(String message){
        super(message);
    }
}
