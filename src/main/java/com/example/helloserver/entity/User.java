package com.example.helloserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user") // 核心：指定表名为 sys_user，和数据库表完全一致！
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
}