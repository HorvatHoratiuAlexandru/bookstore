package com.horvat.bookstore.book.wishlist.exception;

public class ItemAlreadyInTheWishList extends RuntimeException {
    public ItemAlreadyInTheWishList(String message){
        super(message);
    }
}
