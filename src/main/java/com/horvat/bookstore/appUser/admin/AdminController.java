package com.horvat.bookstore.appUser.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.horvat.bookstore.appUser.admin.dtos.PromoCodeCreateDto;
import com.horvat.bookstore.appUser.admin.dtos.ReqCreateBookDto;
import com.horvat.bookstore.appUser.admin.dtos.ResBookUploadedSuccesfullDto;
import com.horvat.bookstore.appUser.admin.dtos.ResSearchResult;
import com.horvat.bookstore.appUser.admin.services.AdminBookService;
import com.horvat.bookstore.appUser.admin.services.AdminPromoCodeService;
import com.horvat.bookstore.appUser.admin.services.BookImageUpload;
import com.horvat.bookstore.book.dtos.responses.ImageInfoDto;
import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BookImageUpload imageUploadService;
    @Autowired
    AdminBookService adminBookService;
    @Autowired
    AdminPromoCodeService adminPromoCodeService;


    @PostMapping("/book-image")
    public List<ImageInfoDto> postMethodName(@RequestParam("files") MultipartFile[] files) { 
        List<ImageInfoDto> response = this.imageUploadService.upload(files);

        
        return response;
    }

    @GetMapping("/search/tag")
    public List<ResSearchResult> searchTags(@RequestParam(value = "search", required = true) String searchText) {
        
        return adminBookService.searchTag(searchText);
    }

    @GetMapping("/search/authors")
    public List<ResSearchResult> searchAuthors(@RequestParam(value = "search", required = true) String searchText) {

        return this.adminBookService.searchAuthor(searchText);
    }
    
    

    @PostMapping("/book")
    public ResBookUploadedSuccesfullDto addBook(@RequestBody ReqCreateBookDto createDto) {
        ResBookUploadedSuccesfullDto response = this.adminBookService.create(createDto);
        
        return response;
    }

    @PutMapping("/book/{bookId}")
    public ResBookDto updateBook(@PathVariable Integer bookId, @RequestParam(required = false) Integer stock, @RequestParam(required = false) Float price) {
        ResBookDto response = this.adminBookService.update(bookId, stock, price);
        
        return response;
    }

    @GetMapping("/orders")
    public String getOrders(@RequestBody String entity) {
        //TODO: get orders by param processed=true/false, finished=true/false, payed=true/false
        
        return entity;
    }

    @PutMapping("/orders/{orderId}")
    public ResBookDto updateOrder(@PathVariable Integer orderId, @RequestParam(required = false) Boolean payed, @RequestParam(required = false) Boolean processed, @RequestParam(required = false) Boolean finished) {
        ResBookDto response;
        
        return response;
    }

    @PutMapping("/promo/{code}")
    public ResPromoDto updatePromoCode(@PathVariable String code) {
        ResPromoDto response = this.adminPromoCodeService.updatePromoCode(code);
        
        return response;
    }

    @PostMapping("/promo")
    public Boolean createPromoCode(@RequestBody PromoCodeCreateDto promoCodeDto) {    
        return this.adminPromoCodeService.createPromoCode(promoCodeDto);
    }

    @GetMapping("promo")
    public List<ResPromoDto> getPromoCodes() {
        return this.adminPromoCodeService.getPromoCodes();
    }
    

}
