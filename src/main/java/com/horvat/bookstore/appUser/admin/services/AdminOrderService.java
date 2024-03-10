package com.horvat.bookstore.appUser.admin.services;

import java.util.List;

import com.horvat.bookstore.order.dtos.responses.ResOrderDto;

public interface AdminOrderService {

    List<ResOrderDto> getAll();
    List<ResOrderDto> updateStatus(Integer orderId, Boolean payed, Boolean processed, Boolean finished);

}
