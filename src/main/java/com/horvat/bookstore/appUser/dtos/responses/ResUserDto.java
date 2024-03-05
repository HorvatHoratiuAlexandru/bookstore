package com.horvat.bookstore.appUser.dtos.responses;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.appUser.RoleModel;
import com.horvat.bookstore.appUser.UserModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUserDto {
    private String id;
    private RoleModel role;
    private String fullName;
    private String email;
    private boolean isGAccount;
    private Boolean active;

    public static ResUserDto fromEntity(UserModel entity){
        if(entity == null) return null;
        ResUserDto response = new ResUserDto();
        BeanUtils.copyProperties(entity, response);
        response.setId(entity.getUid());

        return response;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("user:").append(this.email).append("\n")
        .append("id:").append(this.id).append("\n")
        .append("role:").append(this.role).append("\n")
        .append("status:").append(this.active).append("\n");
        return sb.toString();
    }
}
