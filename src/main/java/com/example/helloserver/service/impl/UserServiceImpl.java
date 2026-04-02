package com.example.helloserver.service.impl;

import com.example.helloserver.common.Result;
import com.example.helloserver.common.ResultCode;
import com.example.helloserver.dto.UserDTO;
import com.example.helloserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    // 纯内存存储，不用数据库，永远不报错
    private static final Map<String, String> userMap = new HashMap<>();

    @Override
    public Result<?> register(UserDTO dto) {
        if (userMap.containsKey(dto.getUsername())) {
            return Result.error(ResultCode.USER_EXIST);
        }
        userMap.put(dto.getUsername(), dto.getPassword());
        return Result.success("注册成功");
    }

    @Override
    public Result<?> login(UserDTO dto) {
        if (!userMap.containsKey(dto.getUsername())) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        if (!userMap.get(dto.getUsername()).equals(dto.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        return Result.success(Map.of("token", "login-success"));
    }
}