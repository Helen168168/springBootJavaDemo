package com.example.springbootdemo.mappers;

import com.example.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User findById(@Param("id") String id);
    void insert(User user);
    List<User> findAll();

    void deleteData(List<String> ids);
}
