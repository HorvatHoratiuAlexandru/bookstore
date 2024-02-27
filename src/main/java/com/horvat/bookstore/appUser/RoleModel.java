package com.horvat.bookstore.appUser;

import org.springframework.security.core.GrantedAuthority;

public enum RoleModel {
    REGULAR,
    ADMIN;

    public GrantedAuthority toGrantedAuthority(){
        return () -> "ROLE_" + this.name();
    }
}
