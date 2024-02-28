package com.horvat.bookstore.configs.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;


@Service
public class CustomJwtAuthenticateServiceImplementation implements TokenAuthenticateService {
    @Value("${myjwt.jwt-secret}")
    private String secret;
    @Value("${myjwt.issuer}")
    private String issuer;



    @Override
    public JwtAuthentication verifyToken(JwtAuthentication token) {
        try {
            JWTVerifier verifier = this.getVerifier();
            DecodedJWT decodedJwt = verifier.verify((String) token.getCredentials());
            
            return new JwtAuthentication((String) token.getCredentials(), JwtTokenStatus.OK, this.getClaimsMap(decodedJwt));
        
        } catch (TokenExpiredException expired) {
            return new JwtAuthentication((String) token.getCredentials(), JwtTokenStatus.EXPIRED, null);
        } catch (SignatureVerificationException | AlgorithmMismatchException sign){
            return new JwtAuthentication((String) token.getCredentials(), JwtTokenStatus.INVALID, null);
        } catch (Exception e){
            return new JwtAuthentication((String) token.getCredentials(), JwtTokenStatus.NA, null);
        }
    }


    public Map<String, ?> getClaimsMap(DecodedJWT decodedJwt) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", decodedJwt.getClaim("id").asString());
        claims.put("username", decodedJwt.getClaim("username").asString());
        claims.put("name", decodedJwt.getClaim("name").asString());
        claims.put("role", decodedJwt.getClaim("role").asString());

        return claims;
    }


    private JWTVerifier getVerifier(){
        Algorithm algo = Algorithm.HMAC256(this.secret);
        return JWT.require(algo).withIssuer(this.issuer).build();
    }

    
}
