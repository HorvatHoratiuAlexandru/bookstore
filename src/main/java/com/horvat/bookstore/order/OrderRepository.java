package com.horvat.bookstore.order;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import java.util.List;
import com.horvat.bookstore.appUser.UserModel;



public interface OrderRepository extends Repository<OrderModel, Integer> {
    OrderModel save(OrderModel order);
    List<OrderModel> findAll();
    Optional<OrderModel> findById(Integer id);
    List<OrderModel> findByUser(UserModel user);
}
