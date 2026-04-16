package com.example.helloserver.common;

import lombok.Data;

@Data
public class Result<T> { // 这里必须加泛型<T>
    private Integer code;
    private String msg;
    private T data;

    // 泛型静态方法
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}