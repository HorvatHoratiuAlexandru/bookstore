package com.horvat.bookstore.appUser.dtos.responses;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.appUser.UserModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Created {
    private String id;
    private Boolean active;
    private Boolean registered;

    public static Created fromEntity(UserModel entity){
        Created createdUser = new Created();
        
        if(entity==null){
            createdUser.registered = false;
            return createdUser;
        }
        
        BeanUtils.copyProperties(entity, createdUser);
        createdUser.setId(entity.getUid());
        createdUser.registered = true;

        return createdUser;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("user created").append("\n")
        .append("id:").append(this.id).append("\n")
        .append("registered:").append(this.registered).append("\n")
        .append("status:").append(this.active).append("\n");
        return sb.toString();
    }
}
