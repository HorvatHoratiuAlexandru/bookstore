package com.horvat.bookstore.promoCode;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PromoCodeController {
    @Autowired
    private PromoCodeService promoCodeService;

    @GetMapping("/user/{id}/promo/{code}")
    public ResPromoDto validatePromoCode(@PathVariable Integer id, @PathVariable String code) {
        ResPromoDto response = this.promoCodeService.validateCode(id, code);
        return response;
    }
    

}
