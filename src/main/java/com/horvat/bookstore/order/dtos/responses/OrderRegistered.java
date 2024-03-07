package com.horvat.bookstore.order.dtos.responses;

import com.horvat.bookstore.order.OrderModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRegistered {
    private Integer orderId;
    private Boolean isPayed;
    private Float total;

    public static OrderRegistered fromEntity(OrderModel order){
        OrderRegistered response = new OrderRegistered();

        if(order == null) return response;

        response.setOrderId(order.getId());
        response.setIsPayed(order.getIsPayed());
        response.setTotal(order.getPrice());
        
        return response;
    }

    public String toString(){
        return "Order:" + this.orderId + " processed:" + this.isPayed + " total price:" + this.total;
    }
}
