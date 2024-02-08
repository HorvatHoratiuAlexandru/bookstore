package com.horvat.bookstore.appUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
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

    
}
