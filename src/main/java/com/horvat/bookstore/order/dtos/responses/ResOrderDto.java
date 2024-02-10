package com.horvat.bookstore.order.dtos.responses;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResOrderDto {
    private Integer id;
    private String date;
    private Float totalPrice;
    private Map<String, Float> items;
    private String address;
    private Boolean processed;
}
