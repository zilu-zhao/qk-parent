package com.qk.interceptor;

import com.qk.utils.CurrentUserHolder;
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
            //获取用户信息：别的功能使用的  不是拦截器内的功能
            // 解析出来后把用户信息放入到claims内
            //获取到用户的id
            Integer userid = claims.get("id", Integer.class);
            //把用户的id赋值给CurrentUserHolder工具类的set方法 上传到线程上
            CurrentUserHolder.setCurrentUser(userid);
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
//该方法是拦截器最后执行的 是服务端完成数据请求之后  返回给前端之前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空线程上的id 防止该线程回到线程池被下次调用的时候继续携带该id，即保证不了数据的安全性也浪费了内存，所以用完清理
        CurrentUserHolder.removeCurrentUser();
    }
}
