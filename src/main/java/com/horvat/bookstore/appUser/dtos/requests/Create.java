package com.horvat.bookstore.appUser.dtos.requests;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.appUser.UserModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Create {
    private String email;
    private String fullName;
    private String password;
    private String repeatPassword;

    public static UserModel getEntity(Create userDto){
        UserModel response = new UserModel();

        BeanUtils.copyProperties(userDto, response);

        return response;
    }
}
