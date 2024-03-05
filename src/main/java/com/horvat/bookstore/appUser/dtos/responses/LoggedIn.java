package com.horvat.bookstore.appUser.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoggedIn {
    private String userId;
    private String token;
    private String refreshToken;

    
}
