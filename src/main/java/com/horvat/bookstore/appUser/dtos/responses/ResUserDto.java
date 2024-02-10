package com.horvat.bookstore.appUser.dtos.responses;

import com.horvat.bookstore.appUser.RoleModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUserDto {
    private Integer id;
    private RoleModel role;
    private String fullName;
    private String email;
    private Boolean active;
}
