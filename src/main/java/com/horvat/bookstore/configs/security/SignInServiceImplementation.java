package com.horvat.bookstore.configs.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.RoleModel;
import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.appUser.dtos.requests.LogIn;
import com.horvat.bookstore.appUser.dtos.responses.LoggedIn;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;
import com.horvat.bookstore.utils.JwtUtils;

@Service
public class SignInServiceImplementation implements JwtSignIn {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Value("${myjwt.jwt-secret}")
    private String secret;
    @Value("${myjwt.issuer}")
    private String issuer;

    @Autowired
    public SignInServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoggedIn signInAndReturnTokens(LogIn credentials) {
        String username = credentials.getEmail();
        String rawPassword = credentials.getPassword();

        Optional<UserModel> userOptional = this.userRepository.findByEmail(username);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User with username" + username + " NotFound");
        }

        if(!this.passwordEncoder.matches(rawPassword, userOptional.get().getPassword())){
            throw new BadCredentialsException("Wrong Password");
        }

        Integer id = userOptional.get().getId();
        RoleModel role = userOptional.get().getRole();

        JwtUtils jwtUtils = new JwtUtils(this.secret, this.issuer);

        return jwtUtils.getTokens(id, username, role);
    }

}
