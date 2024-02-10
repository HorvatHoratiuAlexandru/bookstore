package com.horvat.bookstore.appUser.dtos.requests;

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

    public UserModel getEntityFromDTO(Create userDto){
        return new UserModel();
    }
}
