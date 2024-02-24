package com.horvat.bookstore.appUser.dtos.requests;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqUserDto {
    
    @Size(min=3, max=20)
    private String fullName;
    @Size(min=5, max=20)
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
