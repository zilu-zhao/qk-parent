package com.qk.aop;

import io.lettuce.core.cluster.api.sync.Executions;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/*Aop切面函数，对service层所有的方法功能增强-额外加一个统计运行耗时*/
@Component
@Slf4j
@Aspect  //当前类是一个AOP切面类
public class AopAscept {
    //写范围：所有类模块下的com.qk.service.impl包下的所有类的所有方法(参数不限)
    @Around("execution(* com.qk.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //计算开始之前的时间
        long startTime = System.currentTimeMillis();

        //执行原始方法
        Object proceed = pjp.proceed();

        //计算开始之后的时间
        long end = System.currentTimeMillis();
        //把耗费的时间通过全局异常打印到控制台
        log.error("耗费的时间是："+(end-startTime));

        //返回结果
        return  proceed;
    }
}
