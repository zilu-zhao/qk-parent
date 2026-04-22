package com.qk.Filter;

import com.qk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
@WebFilter("/*")//拦截范围
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
       HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse respose =(HttpServletResponse) servletResponse;

        /*放行登录接口*/
        if ("/login".equals(request.getRequestURI())){
            log.error("登录操作，直接放行");
            //进行下一个过滤 ，如果过滤都完成就请求目标资源
            chain.doFilter(request,respose);
            return;
        }
        //提取请求头中的token
        String token= request.getHeader("token");
        if (token== null || token.isEmpty() ){
            log.error("请求头中没有token，拒绝访问，401 ");
            //告诉浏览器 / 前端：当前用户没有登录，或者登录过期了，拒绝访问。  SC_UNAUTHORIZED=401错误
            respose.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //解析token
        try {
            Claims claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.error("解析token出错，拒绝访问 401");
            respose.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
       log.info("解析正确，允许登录");
        chain.doFilter(request,respose);
    }
}
