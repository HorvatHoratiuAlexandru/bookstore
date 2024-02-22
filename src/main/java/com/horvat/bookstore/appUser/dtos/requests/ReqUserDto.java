package com.horvat.bookstore.appUser.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqUserDto {
    
    private String fullName;
    private String address;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("user update").append("\n")
        .append("fullName:").append(this.fullName).append("\n")
        .append("address:").append(this.address).append("\n");
        return sb.toString();
    }
}
