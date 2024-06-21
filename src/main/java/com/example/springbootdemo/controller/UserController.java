package com.example.springbootdemo.controller;


import com.example.springbootdemo.utils.ResponseResult;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.enums.HttpStatusEnum;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseResult<User> getUserById(@PathVariable String id) {
        try {
            User user = userService.getUserById(id);
            return ResponseResult.ok(user);
        } catch(Exception e) {
            return ResponseResult.error(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/add")
    public ResponseResult<Boolean> addUser(@RequestBody User user) {
       userService.addUser(user);
        return ResponseResult.ok();
    }

    @PostMapping("/users/batch")
    public ResponseResult<Boolean> batchCreateUsers(@RequestBody List<User> users) {
        try {
            for(User user: users) {
                userService.addUser(user);
            }
            return ResponseResult.ok();
        } catch(Exception e) {
            return ResponseResult.error(HttpStatusEnum.FAIL);
        }
    }

    @GetMapping("/users/all")
    public ResponseResult<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseResult.ok(users);

    }

    @DeleteMapping("/users/batchDel")
    public ResponseResult<String> deleteUserById(@RequestBody List<String> ids) {
        userService.deleteUserById(ids);
        return ResponseResult.ok("Deleted " + ids.size() + " users successfully.");
    }
}

