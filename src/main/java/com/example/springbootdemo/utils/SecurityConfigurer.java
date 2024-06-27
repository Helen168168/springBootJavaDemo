package com.example.springbootdemo.utils;

import com.example.springbootdemo.service.MyUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * springSecurity的配置
 * */
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfigurer {

    @Resource
    private MyAuthenticationEntryPoint  myAuthenticationEntryPoint;

    @Resource
    private MyUserDetailsService customUserDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    //请求进入系统时，解析并验证请求中携带的Token，然后构建对应的认证信息（Authentication对象），以便于后续的授权和资源访问控制
    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    //处理数据库或数据访问对象(DAO)的用户认证过程，AuthenticationProvider接口的一个实现，专注于通过查询用户详细信息（如用户名和密码）来验证用户的凭证
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    /**
     * 定义认证管理器AuthenticationManager
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(daoAuthenticationProvider());
        return new ProviderManager(authenticationProviders);

    }
// HttpSecurity: http的请求安全性,提供了大量方法来定义和自定义认证、授权、CSRF保护、会话管理、异常处理、安全头部配置等安全相关的策略
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                                .anyRequest().authenticated())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(configure -> {
                    configure.authenticationEntryPoint(myAuthenticationEntryPoint);
                })
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
