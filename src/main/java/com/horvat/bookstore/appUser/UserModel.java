package com.horvat.bookstore.appUser;

import java.util.Set;
import java.util.UUID;

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
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Setter
@Getter
public class UserModel {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String uid;

    @Column(unique = true, nullable = true)
    private String googleUid;

    @Column(columnDefinition = "boolean default false")
    private boolean isGAccount;

    @Enumerated(EnumType.ORDINAL)
    private RoleModel role;

    @Column
    private String fullName;
    @Column(unique = true)
    private String email;
    @Column
    private String address;
    @Column
    private String password;
    @Column
    private Boolean active;

    @OneToMany(mappedBy = "user")
    private Set<OrderModel> orders;

    @OneToOne(mappedBy = "user")
    private WishListModel wishlist;

    public void autoGenerateUID(){
        this.uid = UUID.randomUUID().toString();
    }
}
