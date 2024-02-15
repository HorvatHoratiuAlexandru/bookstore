package com.horvat.bookstore.appUser.dtos.responses;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.appUser.UserModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Created {
    private Integer id;
    private Boolean active;
    private Boolean registered;

    public static Created fromEntity(UserModel entity){
        Created createdUser = new Created();
        
        if(entity==null){
            createdUser.registered = false;
            return createdUser;
        }
        
        BeanUtils.copyProperties(entity, createdUser);
        createdUser.registered = true;

        return createdUser;
    }
}
