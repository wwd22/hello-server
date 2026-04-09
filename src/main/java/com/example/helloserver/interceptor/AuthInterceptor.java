package com.example.helloserver.interceptor;

import com.example.helloserver.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        // 放行注册和登录
        if (uri.contains("/user/register") || uri.contains("/user/login")) {
            return true;
        }

        // 校验token
        String token = request.getHeader("token");
        if (token == null || token.trim().isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Result<String> result = Result.error("401", "未登录，请先登录");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }

        return true;
    }
}