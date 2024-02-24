package com.horvat.bookstore.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("myjwt")
public record JwtConfigProperties(String jwtSecret, String issuer) {

}
