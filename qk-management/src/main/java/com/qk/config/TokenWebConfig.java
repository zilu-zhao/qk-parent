package com.qk.config;

import com.qk.interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*配置登录拦截器*/
@Slf4j
@Configuration
public class TokenWebConfig implements WebMvcConfigurer {
    /*创建自定义登录拦截器对象*/
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*注册自定义拦截器对象*/
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")   //设置拦截范围
                .excludePathPatterns("/login");//设置排除范围
    }
}
