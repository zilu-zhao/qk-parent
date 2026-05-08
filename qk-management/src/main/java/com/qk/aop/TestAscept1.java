package com.qk.aop;

import io.lettuce.core.cluster.api.sync.Executions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.ProblemDetailJacksonMixin;
import org.springframework.stereotype.Component;

/*Aop切面类，测试不同通知的注解执行的方式*/
/*若有两个切面类，在系统中的执行顺序是按照类名的先后顺序进行执行，如果要指定先执行某一个类
* 加@Order(整数)，整数值越小，切面排序越靠前*/
@Order(1)
@Aspect
@Component
public class TestAscept1 {

    /*切入点表达式的范围都是一样的  防止代码冗余，我们可以把切入点表达式给提取出来
    * 使用注解@pointCut，但是该注解需要加在方法上，需要一个载体，我们创建一个无任何作用的空方法
    * 下边相同的切入点就可以引用这个方法了，如果实在本类中引用 直接写方法名即可，非本类使用需要加路径*/
    @Pointcut("execution(* com.qk.service.impl.*.*(..))")
    public void pointCut(){}
    /*Before通知是在执行范围目标方法之前运行*/
    //该切入点表达式是未使用公共接入点的写法
    @Before("execution(* com.qk.service.impl.*.*(..))")
    public void beforAdvice(ProceedingJoinPoint pjp){
        System.out.println("===========这是before前置通知2");
    }
    /*after最终通知，是在目标方法执行之后运行，且无论是否有异常都会执行*/
    //切入点表达式引用公共切入点
    @After("pointCut()")
    public void afterAdvice(ProceedingJoinPoint pjp){
        System.out.println("==========这是after最终通知2");
    }
    /*afterReturning 后置通知，在目标方法正常执行后执行*/
    @AfterReturning("pointCut()")
    public void afterReturningAdvice(ProceedingJoinPoint pjp) {
        System.out.println("==========这是afterReturning后置通知2");
    }
    /*afterThrowing异常通知，在目标方法爆出异常时才会执行*/
    @AfterThrowing("pointCut()")
    public void afterThrowingAdvice(ProceedingJoinPoint pjp) {
        System.out.println("==========这是afterReturning后置通知2");
    }
}
