package com.example.springbootdemo.service;
import com.example.springbootdemo.mappers.UserMapper;
import com.example.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;
    @Autowired
    public  UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public User getUserById(String id) {
        return userMapper.findById(id);
    }
    public void addUser(User user) {
        userMapper.insert(user);
    }
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    public void deleteUserById(List<String> ids) {
        userMapper.deleteData(ids);
    }
}
