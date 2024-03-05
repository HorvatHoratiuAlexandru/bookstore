package com.horvat.bookstore.book.wishlist;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.wishlist.dtos.responses.ResWishListDto;
import com.horvat.bookstore.configs.aspect.CustomLoggedInUserIdMatch;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@Log4j2
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @CustomLoggedInUserIdMatch
    @GetMapping("/user/{id}/wishlist")
    public List<ResBookDto> getWishList(@PathVariable String id) {
        List<ResBookDto> response = this.wishListService.getUserWishListBooks(id);
        log.info("GET: /user/" + id + "/wishlist returned" + "\n" + response);
        return response;
    }

    @CustomLoggedInUserIdMatch
    @PostMapping("/user/{id}/wishlist/{bookId}")
    public ResWishListDto addToWishList(@PathVariable String id, @PathVariable Integer bookId) {
        ResWishListDto response = this.wishListService.addBookToUserWishList(id, bookId);
        log.info("POST: /user/" + id + "/wishlist/" + bookId + " returned" + "\n" + response );
        return response;
    }

    @CustomLoggedInUserIdMatch
    @DeleteMapping("/user/{id}/wishlist")
    public Boolean clearWishList(@PathVariable String id) {
        Boolean response = this.wishListService.clearUserWishList(id);
        log.info("DELETE: /user/" + id + "/wishlist returned" + "\n" + response);
        return response;
    }

    @CustomLoggedInUserIdMatch
    @DeleteMapping("/user/{id}/wishlist/{bookId}")
    public ResWishListDto removeFromWishList(@PathVariable String id, @PathVariable Integer bookId) {
        ResWishListDto response = this.wishListService.removeBookFromUserWishList(id, bookId);
        log.info("DELETE: /user/" + id + "/wishlist/" + bookId + " returned" + "\n" + response );
        return response;
    }


   
    
    
}
