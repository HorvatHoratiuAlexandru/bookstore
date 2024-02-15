package com.horvat.bookstore.book.wishlist;

import java.util.List;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.wishlist.dtos.responses.ResWishListDto;

public interface WishListService {
    List<ResBookDto> getUserWishListBooks(Integer id);
    ResWishListDto addBookToUserWishList(Integer id, Integer bookId);
    ResWishListDto removeBookFromUserWishList(Integer id, Integer bookId);
    Boolean clearUserWishList(Integer id);
}
