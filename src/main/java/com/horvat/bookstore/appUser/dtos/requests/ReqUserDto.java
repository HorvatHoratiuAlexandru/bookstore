package com.horvat.bookstore.appUser.dtos.requests;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqUserDto {
    
    @Min(3)
    private String fullName;
    @Min(5)
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
