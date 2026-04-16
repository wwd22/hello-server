package com.example.helloserver.entity;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private String realName;
    private String phone;
    private String address;
    private Integer userId;
}