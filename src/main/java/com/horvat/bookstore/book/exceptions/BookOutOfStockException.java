package com.horvat.bookstore.book.exceptions;

public class BookOutOfStockException extends RuntimeException{
    public BookOutOfStockException(String message){
        super(message);
    }
}   
