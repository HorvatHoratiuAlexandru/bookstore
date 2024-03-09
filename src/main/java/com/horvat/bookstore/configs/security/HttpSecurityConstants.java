package com.horvat.bookstore.configs.security;

public class HttpSecurityConstants {
    public static final String[] PUBLIC_GET_PATTERNS = {
        "/book/**",  
    };

    public static final String[] PUBLIC_POST_PATTERNS = {
        "user/register",
        "user/login"
    };

    public static final String[] REGULAR_USER_PATTERNS = {
        "/user/**",
        "/book/*/review",
        "/admin/**"
    };

    public static final String[] ADMIN_USER_PATTERNS = {
        "/book/**",
        "/logs/**" 
    };
}
