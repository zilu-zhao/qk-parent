package com.qk.interceptor;

import com.qk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.swing.*;
/*创建登录拦截器*/
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //第一步：获取请求头中的令牌
        String token = request.getHeader("token");
        //第二步：判断令牌是否不存且或者令牌错误
        if (token == null || token.isEmpty()) {
            log.info("令牌错误");
            //返回错误代码
            response.setStatus(401);
            return false;
        }
        //第三步：解析token，如果解析失败，返回错误结果
        try {
            Claims claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌错误");
            //返回错误代码
            response.setStatus(401);
            return false;
        }
        //第四步：以上情况都没有发生 放行
        log.info("令牌合法，放行");
        return true;
    }
}
