package com.horvat.bookstore.appUser.dtos.requests;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.appUser.UserModel;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Create {
    @Email
    private String email;
    @Size(min=3, max=20)
    private String fullName;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "The password must be atleast 8 character long and contain ONE UPPER LETTER, ONE NUMBER AND ONE SPECIAL SYMBOl")
    private String password;
    private String repeatPassword;

    public static UserModel getEntity(Create userDto){
        UserModel response = new UserModel();
        
        if(userDto==null) return response;

        BeanUtils.copyProperties(userDto, response);

        return response;
    }

    @AssertTrue
    public boolean isPasswordMatching(){
        return this.password.equals(repeatPassword);
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
