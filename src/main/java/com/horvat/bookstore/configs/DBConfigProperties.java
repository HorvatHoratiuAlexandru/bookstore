package com.horvat.bookstore.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mysql")
public record DBConfigProperties(String database, String user, String password, String rootPassword, String host, String port, String url) {

}
