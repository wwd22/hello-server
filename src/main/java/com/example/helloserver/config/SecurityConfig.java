package com.example.helloserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // 开启跨域
                .and()
                .csrf().disable() // 关闭CSRF
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无状态
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()      // 注册放行
                .antMatchers(HttpMethod.POST, "/api/users/login").permitAll() // 登录放行
                .anyRequest().authenticated() // 其他接口必须登录
                .and()
                .formLogin().disable() // 关闭默认登录页
                .httpBasic().disable(); // 关闭基础认证

        return http.build();
    }
}