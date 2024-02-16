package com.horvat.bookstore.promoCode.exception;

public class PromoCodeAlreadyUsed extends RuntimeException {
    public PromoCodeAlreadyUsed(String message){
        super(message);
    }
}
