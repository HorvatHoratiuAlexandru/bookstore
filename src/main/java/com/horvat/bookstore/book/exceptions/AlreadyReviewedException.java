package com.horvat.bookstore.book.exceptions;

public class AlreadyReviewedException extends RuntimeException{
    public AlreadyReviewedException(String message){
        super(message);
    }
}
