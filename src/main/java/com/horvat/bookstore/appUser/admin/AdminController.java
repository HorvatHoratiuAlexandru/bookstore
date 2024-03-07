package com.horvat.bookstore.appUser.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/book")
    public String addBook(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PutMapping("/book")
    public String updateBook(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @DeleteMapping("/book")
    public String deleteBook(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/order/processed")
    public String getAllProcessed(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PostMapping("/order/{orderId}/{status}")
    public String setOrderStatus(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PostMapping("/promo")
    public String createPromoCode(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @DeleteMapping("/promo")
    public String deletePromoCode(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PutMapping("/promo")
    public String updatePromoCode(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

}
