package com.horvat.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.horvat.bookstore.configs.DBConfigProperties;


@SpringBootApplication
@EnableConfigurationProperties(DBConfigProperties.class)
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
}

// TODO: check the path variable with user id to match the logged in user id;