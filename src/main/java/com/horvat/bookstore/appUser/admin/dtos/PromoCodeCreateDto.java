package com.horvat.bookstore.appUser.admin.dtos;

import com.horvat.bookstore.promoCode.PromoCodeModel;

import lombok.Getter;

@Getter
public class PromoCodeCreateDto {
    private String code;
    private Float discount;

    public PromoCodeModel getEntity(){
        PromoCodeModel response = new PromoCodeModel();

        response.setCode(this.code);
        response.setDiscount(this.discount);
        response.setExpired(false);

        return response;
    }
}
