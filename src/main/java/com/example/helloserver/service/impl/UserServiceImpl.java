package com.example.helloserver.service.impl; // 新增impl子包，存放实现类

import com.example.helloserver.dto.UserDTO;
import com.example.helloserver.common.Result;
import com.example.helloserver.common.ResultCode;
import com.example.helloserver.entity.User;
import com.example.helloserver.mapper.UserMapper;
import com.example.helloserver.service.UserService; // 导入修正后的接口
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        if (dbUser != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);
        return Result.success("注册成功！");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        return Result.success("登录成功！");
    }

    @Override
    public Result<String> getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success("查询成功：" + user.getUsername());
    }
}