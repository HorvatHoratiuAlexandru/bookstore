package com.horvat.bookstore.book.wishlist;

import java.util.List;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.wishlist.dtos.responses.ResWishListDto;

public interface WishListService {
    List<ResBookDto> getUserWishListBooks(String id);
    ResWishListDto addBookToUserWishList(String id, Integer bookId);
    ResWishListDto removeBookFromUserWishList(String id, Integer bookId);
    Boolean clearUserWishList(String id);
}
