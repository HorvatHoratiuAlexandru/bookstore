package com.horvat.bookstore.book.wishlist;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.wishlist.dtos.responses.ResWishListDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @GetMapping("/user/{id}/wishlist")
    public List<ResBookDto> getWishList(@PathVariable Integer id) {
        List<ResBookDto> response = this.wishListService.getUserWishListBooks(id);
        
        return response;
    }

    @PostMapping("/user/{id}/wishlist/{bookId}")
    public ResWishListDto addToWishList(@PathVariable Integer id, @PathVariable Integer bookId) {
        ResWishListDto response = this.wishListService.addBookToUserWishList(id, bookId);
        
        return response;
    }

    @DeleteMapping("/user/{id}/wishlist")
    public Boolean clearWishList(@PathVariable Integer id) {
        Boolean response = this.wishListService.clearUserWishList(id);
        
        return response;
    }

    @DeleteMapping("/user/{id}/wishlist/{bookId}")
    public ResWishListDto removeFromWishList(@PathVariable Integer id, @PathVariable Integer bookId) {
        ResWishListDto response = this.wishListService.removeBookFromUserWishList(id, bookId);

        return response;
    }


   
    
    
}
