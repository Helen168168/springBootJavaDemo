package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.User;

import com.example.springbootdemo.utils.CustomUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
/**
 * springsecurity提供的获取用户信息的一个接口
 * */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    private static final Collection<GrantedAuthority> authorities = new ArrayList<>();

    static {
        GrantedAuthority defaultRole = new SimpleGrantedAuthority("common");
        GrantedAuthority xxlJobRole = new SimpleGrantedAuthority("xxl-job");
        authorities.add(defaultRole);
        authorities.add(xxlJobRole);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws AuthenticationException {
        CustomUserDetails userDetails;
        // 这里模拟从数据库中获取用户信息
        if (name.equals("admin")) {
            //这里的admin用户拥有common和xxl-job两个权限
            userDetails = new CustomUserDetails("admin", passwordEncoder.encode("123456"), authorities);
            return userDetails;
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
