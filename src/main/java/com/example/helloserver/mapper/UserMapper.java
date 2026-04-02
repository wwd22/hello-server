package com.example.helloserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.helloserver.entity.User;

// ✅ 把这里的 @Mapper 删掉！
public interface UserMapper extends BaseMapper<User> {
}