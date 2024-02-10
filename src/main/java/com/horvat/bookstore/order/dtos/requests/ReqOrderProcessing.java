package com.horvat.bookstore.order.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqOrderProcessing {
    private String billingName;
    private String cardNumber;
    private String expDate;
    private String cardCode;
}
