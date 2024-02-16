package com.horvat.bookstore.promoCode.exception;

public class PromoCodeNotFound extends RuntimeException {

    public PromoCodeNotFound(String message){
        super(message);
    }
}
