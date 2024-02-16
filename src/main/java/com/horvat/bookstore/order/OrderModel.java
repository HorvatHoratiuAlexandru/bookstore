package com.horvat.bookstore.order;

import java.util.Set;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.promoCode.PromoCodeModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isProcessed;
    @Column 
    private String address;
    @Column
    private Float price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "order")
    private Set<ItemModel> items;

    @ManyToOne
    private PromoCodeModel promoCode;
 
}
