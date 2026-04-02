package com.example.helloserver.common;

public enum ResultCode {
    SUCCESS(200, "操作成功"),
    USER_EXIST(4001, "用户已存在"),
    USER_NOT_EXIST(4002, "用户不存在"),
    PASSWORD_ERROR(4003, "密码错误"),
    TOKEN_INVALID(401, "未登录");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}