package com.horvat.bookstore.appUser.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogIn {
    private String email;
    private String password;
}
