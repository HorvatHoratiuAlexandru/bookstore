package com.horvat.bookstore.promoCode.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoDto {
    private Integer id;
    private String code;
    private Boolean status;
    //TODO: to be changed to Date Time
    private String expDate;
}
