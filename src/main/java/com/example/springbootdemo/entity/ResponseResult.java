package com.example.springbootdemo.entity;

import com.example.springbootdemo.enums.HttpStatusEnum;
import lombok.Data;

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

   public static <T> ResponseResult<T> ok() {
       return new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), null);
   }

    public static <T> ResponseResult<T> ok(T data) {
        ResponseResult<T> res =  new ResponseResult<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), data);
        return res;
    }

    public static <T> ResponseResult<T> error(HttpStatusEnum statusEnum) {
        return new ResponseResult<>(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

}
