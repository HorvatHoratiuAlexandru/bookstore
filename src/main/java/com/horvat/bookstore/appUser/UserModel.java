package com.horvat.bookstore.appUser;

import java.util.List;
import java.util.Set;

import com.horvat.bookstore.book.wishlist.WishListModel;
import com.horvat.bookstore.order.OrderModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "users")
public class UserModel {
    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    private RoleModel role;

    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<OrderModel> orders;

    @OneToOne(mappedBy = "user")
    private WishListModel wishlist;

    
}
