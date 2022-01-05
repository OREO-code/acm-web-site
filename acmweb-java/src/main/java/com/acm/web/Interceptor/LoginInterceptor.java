package com.acm.web.Interceptor;


import cn.hutool.core.util.StrUtil;
import com.acm.web.exception.UserLoginException;
import com.acm.web.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) {
            throw new UserLoginException();
        }
        jwtUtil.getTokenBody(token);
        return true;
    }
}
