package com.horvat.bookstore.appUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {
    
    // Testing, to be rewritten
    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id){
        StringBuilder sb = new StringBuilder();

        sb.append("Hellow User:");
        sb.append(id);
        
        return sb.toString();
    }
}
