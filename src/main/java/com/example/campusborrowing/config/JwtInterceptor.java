package com.example.campusborrowing.config;

import com.example.campusborrowing.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component  // 无意义注解
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            response.setStatus(401);
            return false;
        }
        try {
            JwtUtil.getUserId(token);
        }catch (Exception e){          // token 无效 拦截
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
