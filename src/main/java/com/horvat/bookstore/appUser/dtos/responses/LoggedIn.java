package com.horvat.bookstore.appUser.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoggedIn {
    private Integer userId;
    private String token;
    private String refreshToken;
}
