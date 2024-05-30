package com.example.springbootdemo.service;

import com.example.springbootdemo.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User saveUser(User user);
    void deleteUser(Long id);
}