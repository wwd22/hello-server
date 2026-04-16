package com.example.helloserver.service;

import com.example.helloserver.common.Result;

public interface UserService {
    // 对应泛型Result
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}