package com.qk.config;

import com.qk.interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*配置拦截器*/
@Configuration
public class DemoWebConfig implements WebMvcConfigurer {
/*创建自定义拦截器的对象*/
    @Autowired
    private DemoInterceptor demoInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /*设置拦截器拦截的请求路径*/
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
    }
}
