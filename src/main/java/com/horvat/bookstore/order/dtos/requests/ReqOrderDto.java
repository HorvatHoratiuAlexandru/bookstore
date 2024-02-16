package com.horvat.bookstore.order.dtos.requests;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReqOrderDto {
    private String address;
    private String promoCode;
    //bookId and qty
    private Map<Integer, Integer> items;

}
