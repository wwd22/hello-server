package com.example.helloserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.helloserver.mapper") // 去掉多余的*，直接写包路径即可
public class HelloServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
    }
}