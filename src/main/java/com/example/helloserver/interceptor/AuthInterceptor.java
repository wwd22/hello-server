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
        // 从请求头获取 Authorization 令牌
        String token = request.getHeader("Authorization");

        // 令牌为空时拦截并返回 401
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // 构造统一错误响应
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", ResultCode.TOKEN_INVALID.getCode());
            errorResult.put("msg", ResultCode.TOKEN_INVALID.getMsg());
            errorResult.put("data", null);

            // 输出 JSON 响应
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(errorResult));
            writer.flush();
            return false;
        }

        // 令牌有效，放行
        return true;
    }
}