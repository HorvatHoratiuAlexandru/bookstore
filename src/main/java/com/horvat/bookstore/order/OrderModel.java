package com.horvat.bookstore.order;

import java.util.Set;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.promoCode.PromoCodeModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "order")
    private Set<ItemModel> items;

    @OneToOne
    @JoinColumn(name = "promo_id")
    private PromoCodeModel promoCode;
 
}
