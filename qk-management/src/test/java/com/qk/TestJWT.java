package com.qk;

import com.google.errorprone.annotations.Var;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJWT {
    /*创建令牌*/
    @Test
    public void testGenerateJwt() {
        //创建一个hashmap数组，用来存放要携带得用户信息
        Map<String, Object> claims = new HashMap();
        claims.put("id", 1);
        claims.put("username", "zhaozilu");
        //添加jwt依赖后，调用Jwts工具类的builder方法创建令牌
        String jwt = Jwts.builder()
                //把要携带的个人信息传入到令牌的第二段Payload内
                .setClaims(claims)
                //设置签名的算法和密钥，解析令牌的时候需要该密钥
                .signWith(SignatureAlgorithm.HS256, "itheima")
                //设置令牌的有效期，截止日期，我们设置有效期为一小时后
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 1000))
                //生成令牌
                .compact();
        //令牌为eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzc2ODI0MDkzLCJ1c2VybmFtZSI6InpoYW96aWx1In0.dyk-HJ6_Isgcp_cEFAD9IneZiSdM4xt_0dROPER0WWk
        System.out.println("令牌为" + jwt);
    }
    /*解析令牌*/
    @Test
    public void testJwtParser() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzc2ODI0MDkzLCJ1c2VybmFtZSI6InpoYW96aWx1In0.dyk-HJ6_Isgcp_cEFAD9IneZiSdM4xt_0dROPER0WWk";
        //调用jwts工具类的parser方法进行解析，解析出来是一个Claims对象
        Claims claims = Jwts.parser()
                //传入设置的密钥
                .setSigningKey("itheima")
                //要校验的令牌，我们写在上边的字符串上了
                .parseClaimsJws(jwt)
                //获取令牌内的第二段Payload的信息，我们需要的信息就装在这里
                .getBody();
        System.out.println("信息为：" + claims);
    }
}
