package com.example.helloserver.interceptor;

import com.example.helloserver.common.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头获取 token
        String token = request.getHeader("Authorization");

        // 没有 token 直接拦截
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            Map<String, Object> result = new HashMap<>();
            result.put("code", ResultCode.TOKEN_INVALID.getCode());
            result.put("msg", ResultCode.TOKEN_INVALID.getMsg());
            result.put("data", null);

            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(result));
            writer.flush();
            return false;
        }

        // 有 token 放行
        return true;
    }
}