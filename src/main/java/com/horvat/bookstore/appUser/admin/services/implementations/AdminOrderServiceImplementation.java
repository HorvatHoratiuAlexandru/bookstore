package com.horvat.bookstore.appUser.admin.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.admin.services.AdminOrderService;
import com.horvat.bookstore.order.OrderModel;
import com.horvat.bookstore.order.OrderRepository;
import com.horvat.bookstore.order.dtos.responses.ResOrderDto;
import com.horvat.bookstore.order.exception.OrderNotFound;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AdminOrderServiceImplementation implements AdminOrderService{
    @Autowired
    OrderRepository orderRepository;
    
    @Override
    public List<ResOrderDto> getAll() {
        return ResOrderDto.fromEntityList(this.orderRepository.findAll());    
    }

    @Override
    public List<ResOrderDto> updateStatus(Integer orderId, Boolean payed, Boolean processed, Boolean finished) {
        Optional<OrderModel> entity = this.orderRepository.findById(orderId);

        if(!entity.isPresent()){
            String message = "Order with id: " + orderId + " Not Found";
            log.error(message);
            throw new OrderNotFound(message);
        }

        if(payed != null){
            entity.get().setIsPayed(payed);
        }

        if(processed != null){
            entity.get().setIsProcessed(processed);
        }

        if(finished != null){
            entity.get().setIsFinished(finished);
        }

        this.orderRepository.save(entity.get());

        return this.getAll();
    }

}
