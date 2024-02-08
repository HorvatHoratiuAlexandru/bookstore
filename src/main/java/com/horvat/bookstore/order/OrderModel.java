package com.horvat.bookstore.order;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
