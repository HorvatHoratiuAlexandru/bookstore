package com.horvat.bookstore.book.wishlist;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface WishListRepository extends Repository<WishListModel, Integer> {
    Optional<WishListModel> findById(Integer id);
    WishListModel save(WishListModel wishList);
}
