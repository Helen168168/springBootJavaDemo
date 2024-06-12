package com.example.springbootdemo.service;
import com.example.springbootdemo.mapper.interfaces.UserMapper;
import com.example.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
