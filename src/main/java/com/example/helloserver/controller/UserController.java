package com.example.helloserver.controller;

import com.example.helloserver.common.Result;
import com.example.helloserver.dto.UserDTO;
import com.example.helloserver.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user") // 核心：改为 /user
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @GetMapping("/{id}")
    public Result<String> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}