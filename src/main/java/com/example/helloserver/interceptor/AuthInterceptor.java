package com.example.helloserver.interceptor;

import com.example.helloserver.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 白名单放行登录接口
        String uri = request.getRequestURI();
        if ("/user/login".equals(uri)) {
            return true;
        }

        String token = request.getHeader("token");
        // Java8 兼容：判断空/空格
        if (token == null || token.trim().isEmpty()) {
            token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
        }

        // Java8 兼容判断
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