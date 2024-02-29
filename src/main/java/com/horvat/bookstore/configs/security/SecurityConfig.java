package com.horvat.bookstore.configs.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity()
public class SecurityConfig  {
    @Autowired
    CustomJwtAuthenticateServiceImplementation customJwtSignIn;
    @Autowired
    CustomAuthenticationErrorHandlingFilter errorHandlerFilter;

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .securityMatcher("/user/register", "/user/login")
        .csrf((csrf) -> csrf.disable())
        .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/user/register").permitAll()
        .requestMatchers("/user/login").permitAll()
        .anyRequest().denyAll()
        )
        ;
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainTwo(HttpSecurity http) throws Exception{
        http
        .securityMatchers((customizer) -> customizer.anyRequest() )
        .addFilterBefore(customTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(errorHandlerFilter, CustomTokenAuthenticationFilter.class)
        .csrf((csrf) -> csrf.disable())
        .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
                                            .anyRequest().authenticated()
        )
        ;
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public CustomTokenAuthenticationFilter customTokenAuthenticationFilter(){
        Map<String, TokenAuthenticateService> headerServiceMap = new HashMap<>();
        headerServiceMap.put("Authorization", customJwtSignIn);

        return new CustomTokenAuthenticationFilter(headerServiceMap);
    }

    
}
