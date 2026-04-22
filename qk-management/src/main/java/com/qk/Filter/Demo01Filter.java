package com.qk.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
@Slf4j
//设置拦截范围
/*@WebFilter("/*")*/
public class Demo01Filter implements Filter {
    /*实现jakarta.servlet.Filter接口并重写所有方法*/

    /*初始化方法-在服务器启动时候调用，有且只调用一次，用来初始化（准备环境）*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.error("初始化...");
    }
    /*过滤--每次请求时调用，可以调用多次*/
    /*servletRequest：通过这个对象，可以获取一次请求所有的数据（包括请求方式，请求路径，提交的参数，请求信息等等）
    * servletResponse：通过这个对象，可以设置返回给客户端的一切数据（响应的状态码、响应正文内容、响应头信息等）
    * filterChain：过滤器链对象：只有一个作用：用于放行请求
    * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.error("拦截到了请求，放行前...");
        filterChain.doFilter(servletRequest, servletResponse);//放性
        log.error("拦截到了请求，放行后...");
    }
    /*销毁方法--在服务器停止时调用 ，有且只调用一次 用来做资源清理*/
    @Override
    public void destroy() {
        log.error("销毁....");
    }
}
