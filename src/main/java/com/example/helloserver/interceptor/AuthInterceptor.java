package com.example.helloserver.interceptor;

import com.example.helloserver.common.Result;
import com.example.helloserver.common.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 放行 OPTIONS 跨域预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2. 放行登录、注册接口（核心修复！）
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/users/login")
                || requestURI.startsWith("/api/users/register")
                || requestURI.equals("/api/users")) { // 兼容注册接口
            return true;
        }

        // 3. 其他接口校验 token
        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(Result.error(ResultCode.TOKEN_INVALID)));
            writer.flush();
            writer.close();
            return false;
        }

        // 4. 有token放行
        return true;
    }
}