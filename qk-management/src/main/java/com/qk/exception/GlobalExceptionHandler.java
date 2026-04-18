package com.qk.exception;

import com.qk.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*全局异常处理器*/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleEx(Exception e){//方法形参中指定能够处理的异常类型
        log.error("服务器发生异常", e);
        //捕获到异常之后，响应一个标准的Result
        return Result.error("操作失败,请联系管理员");
    }
}
