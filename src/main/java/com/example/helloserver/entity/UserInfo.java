package com.example.helloserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfo {
    @TableId
    private Long userId;     // 与 sys_user.id 关联
    private String realName;
    private String phone;
    private String address;
}