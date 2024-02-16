package com.horvat.bookstore.order.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String message){
        super(message);
    }
}
