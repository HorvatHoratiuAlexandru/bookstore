package com.horvat.bookstore.promoCode;

import java.util.Set;

import com.horvat.bookstore.order.OrderModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity(name = "promo_code")
@Getter
@Setter
public class PromoCodeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String code;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean expired;
    @Column
    private Float discount;

    @OneToMany(mappedBy="promoCode")
    private Set<OrderModel> orders;

}
