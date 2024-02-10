package com.horvat.bookstore.promoCode;

import com.horvat.bookstore.order.OrderModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity(name = "promo_code")
public class PromoCodeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String code;

    @OneToOne(mappedBy = "promoCode")
    private OrderModel order;
}
