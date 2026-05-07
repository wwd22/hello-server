package com.example.helloserver.controller;

import com.example.helloserver.common.Result;
import com.example.helloserver.entity.UserInfo;
import com.example.helloserver.service.UserService;
import com.example.helloserver.vo.UserDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

    // ===================== 【你缺失的接口】 =====================
    // 注册接口
    @PostMapping
    public Result<String> register() {
        return Result.success("注册成功");
    }

    // 登录接口
    @PostMapping("/login")
    public Result<String> login() {
        return Result.success("登录成功");
    }
    // ===========================================================

    // 查询用户详情（多表联查+Redis）
    @GetMapping("/{id}/detail")
    public Result<UserDetailVO> getUserDetail(@PathVariable("id") Long userId) {
        return userService.getUserDetail(userId);
    }

    // 更新用户扩展信息
    @PutMapping("/{id}/detail")
    public Result<String> updateUserInfo(
            @PathVariable("id") Long userId,
            @RequestBody UserInfo userInfo) {
        userInfo.setUserId(userId);
        return userService.updateUserInfo(userInfo);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }
}