package com.example.helloserver.interceptor;

import com.example.helloserver.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (token == null || token.trim().isEmpty()) {
            response.setContentType("application/json;charset=utf-8");
            // 用 Result.error(String msg) 方法，然后设置 code 为 401
            Result<String> result = Result.error("token无效");
            result.setCode(401);

            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }

        return true;
    }
}