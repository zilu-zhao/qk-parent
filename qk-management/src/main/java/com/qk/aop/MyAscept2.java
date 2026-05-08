package com.qk.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*使用Annotation的方式来写切入点表达式：@Annotation切入点表达式可以根据注解来选择要增强的方法
* 1：我们先新建一个注解 @MyLog，把需要需要增强的连接点加上该注解，然后在切入点表达式写上该注解即可*/
@Aspect
@Component
public class MyAscept2 {
    @Before("@annotation(com.qk.anno.MyLog)")//通知范围是添加了Mylog注解的连接点
    public void before(){
        System.out.println("===========这是使用annotation注解方式添加的前置通知");
    }
}
