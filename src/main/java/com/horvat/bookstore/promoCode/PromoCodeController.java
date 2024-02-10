package com.horvat.bookstore.promoCode;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PromoCodeController {

    @GetMapping("promo/{code}")
    public String validatePromoCode(@PathVariable String code) {
        return new String();
    }
    

}
