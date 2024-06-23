package com.example.springbootdemo.enums;

public enum HttpStatusEnum {
    SUCCESS(200, "操作成功"), //定义常量，SUCCESS是常量名,(200, '操作成功')传递给构造函数的参数
    FAIL(400, "操作失败"),
    NOT_FOUND(404, "未找到"),
    NO_AUTHORIZED(401, "没有权限"),
    NO_LOGIN(403, "用户未登录或已过期"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");


    private int code;
    private String message;
    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}