package com.horvat.bookstore.wishlist;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class WishListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}