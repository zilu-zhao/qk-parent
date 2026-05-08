package com.qk.aop;


import com.aliyun.oss.model.RestoreJobParameters;
import com.qk.entity.OperateLog;
import com.qk.mapper.OperateLogMapper;
import com.qk.utils.CurrentUserHolder;
import net.sf.jsqlparser.statement.select.Join;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*使用Aop对user和dept的增加删除 修改添加一个日志保存的功能 保存到数据库中的日志表*/
@Aspect
@Component
public class AopOperaterLog {
    //最后需要把日志实体类添加到数据库 调用Mapper层的Insert，创建Mapper层对象
    @Autowired
    private OperateLogMapper operateLogMapper;
//使用annotation的切入点表达式，切入点范围是所有加OperaterLog注解的方法
    @Around("@annotation(com.qk.anno.OperaterLog)")//使用annotation注解
    public Object saveRizi(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();//记录开始时间
        Object proceed = pjp.proceed();//执行目标方法
        long end = System.currentTimeMillis();//记录结束时间
        OperateLog operateLog = new OperateLog();//创建日志实体类对象
        //使用JoinPoint的方法和Signature的API获取我们需要的内容
        //通过线程工具类获取到操作人
        operateLog.setOperateUserId(CurrentUserHolder.getCurrentUser());
        //操作时间
        operateLog.setOperateTime(LocalDateTime.now());
        //目标方法的类名
        operateLog.setClassName(pjp.getSignature().getDeclaringTypeName());
        //目标方法的方法名
        operateLog.setMethodName(pjp.getSignature().getName());
        //目标方法的方法形参
        operateLog.setMethodParams(pjp.getArgs().toString());
        //目标方法的返回值
        operateLog.setReturnValue(proceed.toString());
        //执行的时间
        operateLog.setCostTime(end - start);
        //利用Mybatis-plus把operateLog对象添加到日志表中
        operateLogMapper.insert(operateLog);
        return proceed;
    }
}
