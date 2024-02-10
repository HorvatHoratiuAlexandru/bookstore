package com.horvat.bookstore.order;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.order.dtos.requests.ReqOrderDto;
import com.horvat.bookstore.order.dtos.requests.ReqOrderProcessing;
import com.horvat.bookstore.order.dtos.responses.OrderRegistered;
import com.horvat.bookstore.order.dtos.responses.ResOrderDto;
import com.horvat.bookstore.order.dtos.responses.ResOrderProcessing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class OrderController {

    @GetMapping("/user/{id}/orders")
    public List<ResOrderDto> getOrders(@PathVariable Integer id) {
        List<ResOrderDto> response = new ArrayList<>();

        return response;
    }

    @GetMapping("/user/{id}/orders/{orderId}")
    public ResOrderDto getOrder(@PathVariable Integer id, @PathVariable Integer orderId) {
        ResOrderDto response = new ResOrderDto();

        return response;
    }

    
    @PostMapping("/user/{id}/orders")
    public OrderRegistered createOrder(@PathVariable Integer id, @RequestBody ReqOrderDto order) {
        OrderRegistered response = new OrderRegistered();

        return response;
    }

    @PostMapping("/user/{id}/orders/{orderId}/process")
    public ResOrderProcessing postMethodName(@PathVariable Integer id, @PathVariable Integer orderId, @RequestBody ReqOrderProcessing billingData) {
        ResOrderProcessing response = new ResOrderProcessing();
        
        return response;
    }
    
}
