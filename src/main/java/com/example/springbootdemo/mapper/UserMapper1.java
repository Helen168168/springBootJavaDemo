package com.example.springbootdemo.mapper;


import com.example.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper1 {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(String id);

}
