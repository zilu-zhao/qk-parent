package com.qk.utils;

/**
 * 操作当前登录用户信息  工具类
 * ThreadLocal是线程本地变量
 * 拦截器解析令牌之后会获取到用户的信息，我们把用户的信息上传到线程本地变量上进行携带，哪里需要使用
 * 该用户id就可以直接使用get方法进行获取
 */
public class CurrentUserHolder {
    private static final ThreadLocal<Integer> CURRENT_USER = new ThreadLocal<>();
    public static void setCurrentUser(Integer userId) {
        CURRENT_USER.set(userId);
    }
    public static Integer getCurrentUser() {
        return CURRENT_USER.get();
    }
    public static void removeCurrentUser() {
        CURRENT_USER.remove();
    }
}