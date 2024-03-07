package com.horvat.bookstore.order;

import java.util.List;

import com.horvat.bookstore.order.dtos.requests.ReqOrderDto;
import com.horvat.bookstore.order.dtos.requests.ReqOrderProcessing;
import com.horvat.bookstore.order.dtos.responses.OrderRegistered;
import com.horvat.bookstore.order.dtos.responses.ResOrderDto;

public interface OrderService {
    public OrderRegistered placeOrder(String id, ReqOrderDto orderDto);
    public OrderRegistered processOrder(String id, Integer orderId, ReqOrderProcessing billingDto);
    public ResOrderDto getOrder(Integer orderId, String userUid);
    public List<ResOrderDto> getOrders(String userUid);
}
