package com.horvat.bookstore.promoCode;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Log4j2
public class PromoCodeController {
    @Autowired
    private PromoCodeService promoCodeService;

    @GetMapping("/user/{id}/promo/{code}")
    public ResPromoDto validatePromoCode(@PathVariable String id, @PathVariable String code) {
        log.info("GET: /user/" + id + "/promo/" + code);
        ResPromoDto response = this.promoCodeService.validateCode(id, code);
        log.info("GET: /user/" + id + "/promo/" + code + "returned\n" + response);
        return response;
    }
    

}
