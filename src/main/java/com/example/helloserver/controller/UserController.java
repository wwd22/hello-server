package com.example.helloserver.controller;

import com.example.helloserver.common.Result;
import com.example.helloserver.dto.UserDTO;
import com.example.helloserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result<?> register(@RequestBody UserDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserDTO dto) {
        return userService.login(dto);
    }

    @GetMapping("/info")
    public Result<?> info() {
        return Result.success("用户信息");
    }
}