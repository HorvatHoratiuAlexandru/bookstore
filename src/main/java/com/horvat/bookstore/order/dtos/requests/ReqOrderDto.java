package com.horvat.bookstore.order.dtos.requests;

import java.util.Map;

public class ReqOrderDto {
    private Integer id;
    private String address;
    private String date;
    private Map<String, Float> items;
    private Float totalPrice;
}
