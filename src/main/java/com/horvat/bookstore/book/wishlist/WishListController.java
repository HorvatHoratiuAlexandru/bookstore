package com.horvat.bookstore.book.wishlist;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class WishListController {

    @GetMapping("/user/{id}/wishlist")
    public List<ResBookDto> getWishList(@PathVariable Integer id) {
        List<ResBookDto> response = new ArrayList<>();
        return response;
    }

    @PostMapping("/user/{id}/wishlist/{bookId}")
    public Boolean addToWishList(@PathVariable Integer id, @PathVariable Integer bookId) {
        Boolean response = true;
        return response;
    }

    @DeleteMapping("/user/{id}/wishlist")
    public Boolean clearWishList(@PathVariable Integer id) {
        Boolean response = true;
        return response;
    }

    @DeleteMapping("/user/{id}/wishlist/{bookId}")
    public Boolean removeFromWishList(@PathVariable Integer id, @PathVariable Integer bookId) {
        Boolean response = true;
        return response;
    }


   
    
    
}
