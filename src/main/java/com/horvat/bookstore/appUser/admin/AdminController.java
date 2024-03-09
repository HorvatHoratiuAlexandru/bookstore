package com.horvat.bookstore.appUser.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.horvat.bookstore.appUser.admin.services.BookImageUpload;
import com.horvat.bookstore.book.dtos.responses.ImageInfoDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BookImageUpload imageUploadService;



    @PostMapping("book-image")
    public List<ImageInfoDto> postMethodName(@RequestParam("files") MultipartFile[] files) {
        
        List<ImageInfoDto> response = this.imageUploadService.upload(files);

        
        return response;
    }
    

    @PostMapping("/book")
    public String addBook(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PutMapping("/book")
    public String updateBook(@RequestBody String entity) {
        //TODO: process request
        
        return entity;
    }

    @DeleteMapping("/book")
    public String deleteBook(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/orders")
    public String getOrders(@RequestBody String entity) {
        //TODO: get orders by param processed=true/false, finished=true/false, payed=true/false
        
        return entity;
    }

    @PutMapping("/order/{orderId}")
    public String updateOrder(@RequestBody String entity) {
        //TODO: should change order status
        
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
