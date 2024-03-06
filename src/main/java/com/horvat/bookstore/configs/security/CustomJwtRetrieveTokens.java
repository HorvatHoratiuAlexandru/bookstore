package com.horvat.bookstore.configs.security;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    public LoggedIn refreshTokens(String pastToken){
        DecodedJWT decodedPastToken = this.decode(pastToken);
        String uid = decodedPastToken.getClaims().get("id").asString();

        List<UserModel> userQueryResult = this.userRepository.findByUid(uid);
        if(userQueryResult == null || userQueryResult.isEmpty()){
            throw new UserNotFoundException("The token uid does not correspond to a registered user");
        }
        ResUserDto userDto = ResUserDto.fromEntity(userQueryResult.get(0));
        String token = getToken(15, userDto);
        String refreshToken = getToken(45, userDto);

        return new LoggedIn(uid, token, refreshToken);
    }

    private String getToken(Integer expires, ResUserDto user){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, expires);     

        Algorithm algo = this.getAlgorithm();
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

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(this.secret);
    }

    private DecodedJWT decode(String jwt){
        try {
            JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
            DecodedJWT decodedJwt = verifier.verify(jwt);

            return decodedJwt;
        } catch (Exception e) {
            throw new BadCredentialsException("Bad refresh token");
        }
    }
}
