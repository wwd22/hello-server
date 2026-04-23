package com.example.helloserver.service;

import com.example.helloserver.common.Result;
import com.example.helloserver.entity.UserInfo;
import com.example.helloserver.vo.UserDetailVO;

public interface UserService {
    Result<UserDetailVO> getUserDetail(Long userId);
    Result<String> updateUserInfo(UserInfo userInfo);
    Result<String> deleteUser(Long userId);
}