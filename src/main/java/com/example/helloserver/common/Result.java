package com.example.helloserver.common;

import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    /**
     * 成功返回（带数据）
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功返回（无数据）
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 失败返回（自定义状态码+提示信息）
     */
    public static <T> Result<T> error(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败返回（默认500状态码+自定义提示）
     */
    public static <T> Result<T> error(String msg) {
        return error("500", msg);
    }
}