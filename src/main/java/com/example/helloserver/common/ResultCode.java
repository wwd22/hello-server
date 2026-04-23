package com.example.helloserver.common;

import lombok.Data;

public enum ResultCode {
    USER_NOT_EXIST(404, "用户不存在");

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