package com.horvat.bookstore.appUser.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogIn {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min=8)
    private String password;

}
