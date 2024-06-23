package com.example.springbootdemo.controller;

import com.example.springbootdemo.utils.ResponseResult;
import com.example.springbootdemo.utils.ServerException;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @return 返回登录结果
     */
    @GetMapping("/usernamePwd")
    public ResponseResult<?> usernamePwd(String username, String password) {
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
