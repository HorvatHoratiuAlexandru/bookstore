package com.horvat.bookstore.order;

import com.horvat.bookstore.order.dtos.requests.ReqOrderDto;
import com.horvat.bookstore.order.dtos.requests.ReqOrderProcessing;
import com.horvat.bookstore.order.dtos.responses.OrderRegistered;

public interface OrderService {
    public OrderRegistered placeOrder(Integer id, ReqOrderDto orderDto);
    public OrderRegistered processOrder(Integer id, Integer orderId, ReqOrderProcessing billingDto);
}
