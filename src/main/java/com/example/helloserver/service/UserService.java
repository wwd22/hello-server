package com.example.helloserver.service; // 修正为实际包名

import com.example.helloserver.dto.UserDTO; // 导入UserDTO（修正路径）
import com.example.helloserver.common.Result; // 导入Result（修正路径）

// 泛型+异常声明与实现类严格对齐
public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
    Result<String> getUserById(Long id);
}