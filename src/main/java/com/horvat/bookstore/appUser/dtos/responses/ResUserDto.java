package com.horvat.bookstore.appUser.dtos.responses;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.appUser.RoleModel;
import com.horvat.bookstore.appUser.UserModel;

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

    public static ResUserDto fromEntity(UserModel entity){
        ResUserDto response = new ResUserDto();
        if(!(entity==null)) BeanUtils.copyProperties(entity, response);

        return response;
    }
}
