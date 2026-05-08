package com.qk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*自定义注解、
* 注解上要添加元注解
*   1：@Target：用于限制 自定义注解在哪里使用
*   2：@Retention：用于设置自定义注解可以保留到什么时候， 我们自定义的注解通常保留都爱运行时
* */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
}
