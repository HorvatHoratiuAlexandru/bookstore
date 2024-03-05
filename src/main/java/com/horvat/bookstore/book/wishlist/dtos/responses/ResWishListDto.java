package com.horvat.bookstore.book.wishlist.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.wishlist.WishListModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResWishListDto {
    private Integer id;
    private String userId;
    private List<ResBookDto> books;


    public static ResWishListDto fromEntity(WishListModel wishList){
        ResWishListDto response = new ResWishListDto();

        response.setId(wishList.getId());
        response.setUserId(wishList.getUser().getUid());
        response.setBooks(ResBookDto.fromIterableEntity(new ArrayList<BookModel>(wishList.getBooks())));

        return response;
    }

    public String toString(){
        return "wishlist:" + id + "\n" + "user:" + userId + "\n" + books;
    }
}
