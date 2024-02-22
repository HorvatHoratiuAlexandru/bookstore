package com.horvat.bookstore.appUser.dtos.requests;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogIn {
    @Email
    private String email;
    private String password;
}
