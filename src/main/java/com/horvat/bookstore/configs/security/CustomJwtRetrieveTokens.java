package com.horvat.bookstore.configs.security;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.appUser.dtos.requests.LogIn;
import com.horvat.bookstore.appUser.dtos.responses.LoggedIn;
import com.horvat.bookstore.appUser.dtos.responses.ResUserDto;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;

@Service
public class CustomJwtRetrieveTokens {
 @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Value("${myjwt.jwt-secret}")
    private String secret;
    @Value("${myjwt.issuer}")
    private String issuer;


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
        ResUserDto userDto = ResUserDto.fromEntity(userOptional.get());
        String token = getToken(15, userDto);
        String refreshToken = getToken(45, userDto);
        
        
        return new LoggedIn(userDto.getId(), token, refreshToken);
    }

    private String getToken(Integer expires, ResUserDto user){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, expires);     

        Algorithm algo = Algorithm.HMAC256(this.secret);
        String token = JWT.create()
                        .withIssuer(this.issuer)
                        .withClaim("id", user.getId())
                        .withClaim("username", user.getEmail())
                        .withClaim("name", user.getFullName())
                        .withClaim("role", user.getRole().toString())
                        .withIssuedAt(new Date())
                        .withExpiresAt(cal.getTime())
                        .sign(algo);

        return token;
    }
}
