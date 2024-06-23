package com.example.springbootdemo.utils;

import org.springframework.security.core.AuthenticationException;

public class AuthorizationExceptionEx extends AuthenticationException {

    public AuthorizationExceptionEx(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthorizationExceptionEx(String msg) {
        super(msg);
    }

}
