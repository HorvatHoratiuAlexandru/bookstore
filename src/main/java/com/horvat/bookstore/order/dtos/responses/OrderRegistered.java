package com.horvat.bookstore.order.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRegistered {
    private Integer orderId;
    private Boolean processed;
}
