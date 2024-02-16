package com.horvat.bookstore.promoCode.exception;

public class PromoCodeExpired extends RuntimeException {
    public PromoCodeExpired(String message){
        super(message);
    }
}
