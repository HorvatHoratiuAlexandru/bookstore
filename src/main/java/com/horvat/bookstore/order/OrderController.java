package com.horvat.bookstore.order;

import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.order.dtos.requests.ReqOrderDto;
import com.horvat.bookstore.order.dtos.requests.ReqOrderProcessing;
import com.horvat.bookstore.order.dtos.responses.OrderRegistered;
import com.horvat.bookstore.order.dtos.responses.ResOrderDto;
import com.horvat.bookstore.order.dtos.responses.ResOrderProcessing;
import com.horvat.bookstore.promoCode.PromoCodeService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PromoCodeService promoCodeService;

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
    public OrderRegistered placeOrder(@PathVariable Integer id, @RequestBody ReqOrderDto order) {
        if(order.getPromoCode() != null){
            //TODO: throw error if code expired/notexist or already used
            this.promoCodeService.validateCode(id, order.getPromoCode());
        }
        
        OrderRegistered response = this.orderService.placeOrder(id, order);

        return response;
    }

    @PostMapping("/user/{id}/orders/{orderId}/process")
    public OrderRegistered processOrder(@PathVariable Integer id, @PathVariable Integer orderId, @RequestBody ReqOrderProcessing billingData) {
        OrderRegistered response = this.orderService.processOrder(id, orderId, billingData);
        
        return response;
    }
    
}
