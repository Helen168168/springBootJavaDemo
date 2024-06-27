package com.example.springbootdemo.controller;

import com.example.springbootdemo.utils.ResponseResult;
import com.example.springbootdemo.utils.ServerException;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager; //认证管理器处理认证请求

    /**
     * 用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @return 返回登录结果
     */
    @GetMapping("/usernamePwd")
    public ResponseResult<?> usernamePwd(String username, String password) {
        //spring security的核心，用于封装用户凭证(用户名和密码)进行认证处理， principal存储用户信息或者用户实体对象，credentials用于存储用户凭证比如密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new ServerException(e.getMessage());
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        return ResponseResult.ok(token);
    }
}
