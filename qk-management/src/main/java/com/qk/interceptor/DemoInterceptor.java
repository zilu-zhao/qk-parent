package com.qk.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*自定义拦截器*/
@Component   //加bean注解 方便配置拦截器时创建该类的对象
public class DemoInterceptor implements HandlerInterceptor {
    /*该方法是拦截项目启动到controller层这一段范围的*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;//true 为放行，false为拦截
    }
/*该方法是处理从controller放行之后 到 service 到mapper 在返回到controller层 这一段范围的*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }
/*该方法是处理最终回调的，从数据完成之后到项目结束这一段范围的*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
