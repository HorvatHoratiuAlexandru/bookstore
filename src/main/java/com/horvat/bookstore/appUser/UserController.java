package com.horvat.bookstore.appUser;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController()
@RequestMapping("/user")
@Log4j2
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResUserDto getUserById(@PathVariable Integer id){
        ResUserDto response = this.userService.getUser(id);
        log.info("GET:/user/" + id + " returned:\n" + response);
        return response;
    }

    @PostMapping("/register")
    public Created register(@RequestBody Create userDto) {
        log.info("POST:/user/register"+" recived:\n" + userDto);
        Created response = this.userService.createUser(userDto);
        log.info("POST:/user/register"+" returned:\n" + response);
        return response;
    }

    @PostMapping("/login")
    public LoggedIn logIn(@RequestBody LogIn credentials) {
        LoggedIn response = new LoggedIn();
        
        return response;
    }

    @PutMapping("/{id}")
    public ResUserDto putUser(@PathVariable Integer id, @RequestBody ReqUserDto userDto) {
        log.info("PUT:/user/" + id + " recieved:\n" + userDto);
        ResUserDto response = this.userService.updateUser(id, userDto);
        log.info("PUT:/user/" + id + " returned:\n" + response);
        return response;
    }

    @PostMapping("/activate/{activationCode}")
    public ResUserDto activateAccount(@PathVariable String activationCode){
        log.info("POST:/user/activate/" + activationCode);
        ResUserDto response = this.userService.activateUser(activationCode);
        log.info("POST:/user/activate/" + activationCode + " returned:\n" + response);
        return response;
    }
    

}
