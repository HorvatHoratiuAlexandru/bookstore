package com.horvat.bookstore.order.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResOrderProcessing {
    private Integer orderId;
    private Boolean processing;
}
