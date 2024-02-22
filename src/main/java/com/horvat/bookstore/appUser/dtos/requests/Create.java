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
        
        if(userDto==null) return response;

        BeanUtils.copyProperties(userDto, response);

        return response;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("create user").append("\n")
        .append("email:").append(this.email).append("\n")
        .append("full_name:").append(this.fullName).append("\n");
        return sb.toString();
    }
}
