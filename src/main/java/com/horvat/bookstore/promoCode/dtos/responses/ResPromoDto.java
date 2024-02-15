package com.horvat.bookstore.promoCode.dtos.responses;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.promoCode.PromoCodeModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResPromoDto {
    private Integer id;
    private String code;
  

    public static ResPromoDto fromEntity(PromoCodeModel code){
        ResPromoDto response = new ResPromoDto();
        if(code == null) return response;

        BeanUtils.copyProperties(code, response);

        return response;
    }

}
