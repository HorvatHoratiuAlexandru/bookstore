package com.horvat.bookstore.promoCode.dtos.responses;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.promoCode.PromoCodeModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResPromoDto {
    private Integer id;
    private String code;
    private Float discount;
    private Boolean expired;
  

    public static ResPromoDto fromEntity(PromoCodeModel code){
        ResPromoDto response = new ResPromoDto();
        if(code == null) return response;

        BeanUtils.copyProperties(code, response);

        return response;
    }

    public static List<ResPromoDto> fromEntityList(List<PromoCodeModel> codes){
        List<ResPromoDto> response = new LinkedList<>();

        for(PromoCodeModel c : codes){
            response.add(ResPromoDto.fromEntity(c));
        }

        return response;
    }

    @Override
    public String toString() {
        return "ResPromoDto\n" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount;
    }

}
