package com.horvat.bookstore.order;

import java.util.Optional;

import org.springframework.data.repository.Repository;


public interface OrderRepository extends Repository<OrderModel, Integer> {
    OrderModel save(OrderModel order);
    Optional<OrderModel> findById(Integer id);
}
