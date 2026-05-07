package com.example.helloserver.interceptor;

import com.example.helloserver.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 白名单：直接放行注册和登录接口
        String uri = request.getRequestURI();
        if ("/api/users".equals(uri) || "/api/users/login".equals(uri)) {
            return true;
        }

        String token = request.getHeader("token");

        if (token == null || token.trim().isEmpty()) {
            response.setContentType("application/json;charset=utf-8");
            Result<String> result = Result.error("token无效");
            result.setCode(401);

            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }

        return true;
    }
}