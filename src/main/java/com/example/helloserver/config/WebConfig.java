package com.example.helloserver.config;

import com.example.helloserver.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**") // 只拦截 /api 开头的业务接口，不拦截根路径
                .excludePathPatterns(
                        "/api/users/login",    // 登录接口
                        "/api/users/register", // 注册接口（如果有）
                        "/",                   // 根路径
                        "/error",              // 错误页面
                        "/static/**",          // 静态资源
                        "/index.html"          // 首页（如果有）
                );
    }
}