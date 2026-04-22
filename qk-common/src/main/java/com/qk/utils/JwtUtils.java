package com.qk.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    /*自己封装的工具类
    * 创建令牌的方法：调用generateToken方法传入值 就创建了，传入值的方式为map
    * 解析令牌的方法：调用parseToken方法传入令牌就解析为对象*/
    private static final String SECRET_KEY = "cWluZ2tl";// 秘钥
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000; // 令牌有效期（12小时）

     //生成JWT令牌
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

     // 解析JWT令牌
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}