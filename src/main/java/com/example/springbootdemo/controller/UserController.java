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
    public void addUser(@RequestBody User user) {
       userService.addUser(user);
        return;
    }

    @PostMapping("/users/batch")
    public ResponseEntity<String> batchCreateUsers(@RequestBody List<User> users) {
        for(User user: users) {
            userService.addUser(user);
        }
        return ResponseEntity.ok("批量创建用户成功");
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/batchDel")
    public ResponseEntity<String> deleteUserById(@RequestBody List<String> ids) {
        userService.deleteUserById(ids);
        return ResponseEntity.ok("Deleted " + ids.size() + " users successfully.");
    }
}

