package com.example.springbootdemo.utils;

import com.example.springbootdemo.enums.HttpStatusEnum;
import lombok.Data;

/**
 * 消息响应主体
 * @author pxm
 * */
@Data
public class ResponseResult<T> {
   private int code;
   private String message;
   private  T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

   //无需返回data
    public static <T> ResponseResult<T> ok() {
       return new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), null);
    }

    public static <T> ResponseResult<T> ok(String message) {
        return new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), message, null);
    }
    //需要返回数据
    public static <T> ResponseResult<T> ok(T data) {
        ResponseResult<T> res =  new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), data);
        return res;
    }

    public static <T> ResponseResult<T> ok(T data, String message) {
        ResponseResult<T> res =  new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), message, data);
        return res;
    }

    //请求失败
    public static <T> ResponseResult<T> error(HttpStatusEnum statusEnum) {
        return new ResponseResult<>(statusEnum.getCode(), statusEnum.getMessage(), null);
    }
    public static <T> ResponseResult<T> error(HttpStatusEnum statusEnum, String message) {
        return new ResponseResult<>(statusEnum.getCode(), message, null);
    }

}
