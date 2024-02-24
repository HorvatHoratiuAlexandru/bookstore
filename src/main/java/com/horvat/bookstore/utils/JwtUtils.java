package com.horvat.bookstore.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.horvat.bookstore.appUser.RoleModel;
import com.horvat.bookstore.appUser.dtos.responses.LoggedIn;

public class JwtUtils {
    private String secret;
    private String issuer;

    public JwtUtils(String secret, String issuer){
        this.secret=secret;
        this.issuer=issuer;
    }
    public LoggedIn getTokens(Integer id, String username, RoleModel role){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 15);     

        Algorithm algo = Algorithm.HMAC256(this.secret);
        String token = JWT.create()
                        .withIssuer(this.issuer)
                        .withClaim("id", id)
                        .withClaim("username", username)
                        .withClaim("role", role.toString())
                        .withIssuedAt(new Date())
                        .withExpiresAt(cal.getTime())
                        .sign(algo);

        cal.add(Calendar.HOUR_OF_DAY, 2);
        String refreshToken = JWT.create()
                        .withIssuer(this.issuer)
                        .withClaim("id", id)
                        .withClaim("username", username)
                        .withClaim("role", role.toString())
                        .withIssuedAt(new Date())
                        .withExpiresAt(cal.getTime())
                        .sign(algo);

        LoggedIn response = new LoggedIn();
        response.setToken(token);
        response.setRefreshToken(refreshToken);
        response.setUserId(id);

        return response;
    }

    public boolean verifyToken(String token){
        try {
            Algorithm algo = Algorithm.HMAC256(this.secret);
            JWTVerifier verifier = JWT.require(algo).withIssuer(this.issuer).build();
            verifier.verify(token);
        
        } catch (Exception e) {
            // Might throw the exception and handle it in controller advice?
            return false;
        }

        return true;
    }

    public String getUsernameFromClaims(String token){
        Algorithm algo = Algorithm.HMAC256(this.secret);
        JWTVerifier verifier = JWT.require(algo).withIssuer(this.issuer).build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim("username");
        
        if(claim.isNull()){
            return null;
        }
        
        return claim.asString();
    }

    public RoleModel getRoleFromClaims(String token){
        Algorithm algo = Algorithm.HMAC256(this.secret);
        JWTVerifier verifier = JWT.require(algo).withIssuer(this.issuer).build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim("role");
    
        try {
            return RoleModel.valueOf(claim.asString());
        } catch (Exception e) {
            throw e;
        }
    }

    public Integer getIdFromClaims(String token){
        Algorithm algo = Algorithm.HMAC256(this.secret);
        JWTVerifier verifier = JWT.require(algo).withIssuer(this.issuer).build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim("id");

        if(claim.isNull()){
            return null;
        }

        return claim.asInt();
    }

    
}
