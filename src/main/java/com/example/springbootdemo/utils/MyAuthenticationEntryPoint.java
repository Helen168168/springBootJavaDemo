package com.example.springbootdemo.utils;

import com.example.springbootdemo.enums.HttpStatusEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 自定义的异常处理类，用于处理认证异常及访问被拒绝异常
 * */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ResponseResult<String> result = ResponseResult.error(HttpStatusEnum.NO_LOGIN, "用户未登录或已过期");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJson(result));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<String> result = ResponseResult.error(HttpStatusEnum.NO_LOGIN, "权限不足");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJson(result));
    }
}
