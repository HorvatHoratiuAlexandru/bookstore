package com.horvat.bookstore.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seed")
public record DataSeedingConfigProperties(String titleFolder) {

} 