package com.example.helloserver.service.impl;

import cn.hutool.json.JSONUtil;
import com.example.helloserver.common.Result;
import com.example.helloserver.common.ResultCode;
import com.example.helloserver.entity.UserInfo;
import com.example.helloserver.mapper.UserInfoMapper;
import com.example.helloserver.service.UserService;
import com.example.helloserver.vo.UserDetailVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    // 缓存key前缀
    private static final String CACHE_KEY_PREFIX = "user:detail:";

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<UserDetailVO> getUserDetail(Long userId) {
        String key = CACHE_KEY_PREFIX + userId;

        // 1. 先查缓存（加try-catch兼容Redis未启动的情况）
        String json = null;
        try {
            json = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception ignored) {}

        if (json != null && !json.trim().isEmpty()) {
            try {
                UserDetailVO cacheVO = JSONUtil.toBean(json, UserDetailVO.class);
                return Result.success(cacheVO);
            } catch (Exception e) {
                // 缓存解析出错，删除脏数据
                try {
                    stringRedisTemplate.delete(key);
                } catch (Exception ignored) {}
            }
        }

        // 2. 查数据库
        UserDetailVO detail = userInfoMapper.getUserDetail(userId);
        if (detail == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 3. 写缓存（同样加try-catch）
        try {
            stringRedisTemplate.opsForValue().set(
                    key,
                    JSONUtil.toJsonStr(detail),
                    10,
                    TimeUnit.MINUTES
            );
        } catch (Exception ignored) {}

        return Result.success(detail);
    }

    @Override
    @Transactional
    public Result<String> updateUserInfo(UserInfo userInfo) {
        if (userInfo == null || userInfo.getUserId() == null) {
            return Result.error("参数不能为空");
        }
        // 更新数据库
        userInfoMapper.updateById(userInfo);
        // 删除旧缓存
        try {
            stringRedisTemplate.delete(CACHE_KEY_PREFIX + userInfo.getUserId());
        } catch (Exception ignored) {}
        return Result.success("更新成功");
    }

    @Override
    @Transactional
    public Result<String> deleteUser(Long userId) {
        // 删除数据库
        userInfoMapper.deleteById(userId);
        // 删除缓存
        try {
            stringRedisTemplate.delete(CACHE_KEY_PREFIX + userId);
        } catch (Exception ignored) {}
        return Result.success("删除成功");
    }
}