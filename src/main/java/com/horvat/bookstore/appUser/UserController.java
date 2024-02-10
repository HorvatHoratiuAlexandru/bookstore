package com.horvat.bookstore.appUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.appUser.dtos.requests.Create;
import com.horvat.bookstore.appUser.dtos.requests.LogIn;
import com.horvat.bookstore.appUser.dtos.requests.ReqUserDto;
import com.horvat.bookstore.appUser.dtos.responses.Created;
import com.horvat.bookstore.appUser.dtos.responses.LoggedIn;
import com.horvat.bookstore.appUser.dtos.responses.ResUserDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController()
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public ResUserDto getUserById(@PathVariable Integer id){
        ResUserDto response = new ResUserDto();
        
        return response;
    }

    @PostMapping("/register")
    public Created postMethodName(@RequestBody Create userDto) {
        Created response = new Created();

        return response;
    }

    @PostMapping("/login")
    public LoggedIn logIn(@RequestBody LogIn credentials) {
        LoggedIn response = new LoggedIn();
        
        return response;
    }

    @PutMapping("/{id}")
    public ResUserDto putUser(@PathVariable Integer id, @RequestBody ReqUserDto userDto) {
        ResUserDto response = new ResUserDto();
        
        return response;
    }
    

}
