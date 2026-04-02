package com.example.helloserver.service;

import com.example.helloserver.common.Result;
import com.example.helloserver.dto.UserDTO;

public interface UserService {
    Result<?> register(UserDTO dto);
    Result<?> login(UserDTO dto);
}