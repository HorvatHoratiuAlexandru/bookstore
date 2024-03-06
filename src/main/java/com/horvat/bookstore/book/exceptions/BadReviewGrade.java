package com.horvat.bookstore.book.exceptions;

public class BadReviewGrade extends RuntimeException {

    public BadReviewGrade(String message){
        super(message);
    }
}
