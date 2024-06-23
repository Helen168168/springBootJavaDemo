package com.example.springbootdemo.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User {

    private String id;

    private String name;

    private String password;


    private List<String> roles;

    public CustomUserDetails(String name, String password, Collection<? extends GrantedAuthority> authorities) {
        super(name, password, authorities);
    }

    public CustomUserDetails(String name, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(name, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
