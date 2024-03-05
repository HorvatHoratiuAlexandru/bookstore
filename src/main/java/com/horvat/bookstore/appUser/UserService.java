package com.horvat.bookstore.appUser;

import com.horvat.bookstore.appUser.dtos.requests.Create;
import com.horvat.bookstore.appUser.dtos.requests.ReqUserDto;
import com.horvat.bookstore.appUser.dtos.responses.Created;
import com.horvat.bookstore.appUser.dtos.responses.ResUserDto;

public interface UserService {
    ResUserDto getUser(String id);
    Created createUser(Create newUserDto);
    ResUserDto updateUser(String id, ReqUserDto updateDto);
    ResUserDto activateUser(String activationCode);
}
