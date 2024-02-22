package com.horvat.bookstore.order.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqOrderProcessing {
    private String billingName;
    private String cardNumber;
    private String expDate;
    private String cardCode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReqOrderProcessing");
        sb.append("billingName='").append(billingName).append('\'');
        if (cardNumber != null && cardNumber.length() >= 5) {
            sb.append(", lastFiveDigits='").append(cardNumber.substring(cardNumber.length() - 5)).append('\'');
        }
        sb.append(", expDate='").append(expDate).append('\'');
        return sb.toString();
    }
}
