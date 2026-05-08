package com.qk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    /**
     * 如果我们想要操作Redis，就需要注入这个对象
     * 这个对象的泛型：Key的类型，value的类型
     */
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    /**
     * RedisTemplate提供了操作不同数据类型的方法
     *  如果要操作string类型：redisTemplate.opsForValue().....
     *  如果要操作hash类型：  redisTemplate.opsForHash().....
     *  如果要操作list类型：  redisTemplate.opsForList().....
     *  如果要操作set类型：   redisTemplate.opsForSet().....
     *  如果要操作zset类型：  redisTemplate.opsForZSet().....
     */
    //操作spring类型
    @Test
    public void testString(){
        //存数据  set name zl
        redisTemplate.opsForValue().set("name" ,"zl");
        //取数据  get name
        System.out.println(redisTemplate.opsForValue().get("name"));
        //存数据并设置过期时间： get(键，值，过期时间，时间单位)
        redisTemplate.opsForValue().set("gender","男",60, TimeUnit.SECONDS);
        //取数据
        System.out.println(redisTemplate.opsForValue().get("gender"));
    }
    @Test
    public void testHash(){
        //存储hash类型数据  hset key field value
        redisTemplate.opsForHash().put("user1","name","zhaozilu");
        redisTemplate.opsForHash().put("user1","age",23);

        //取hash数据  hget key field
        Object name = redisTemplate.opsForHash().get("user1", "name");
        Object age = redisTemplate.opsForHash().get("user1", "age");
        System.out.println("name="+name);
        System.out.println("age = " + age);
    }
    @Test
    public void testList(){
        //存储list类型的数据 lpush key value1 value2 value3....
        redisTemplate.opsForList().leftPushAll("girls","zhaoxiujuan","zhujunyao","niejingjing","zhangsiyu");
        //取数据rpop key  一个一个取
        Object girls = redisTemplate.opsForList().rightPop("girls");
        System.out.println("girls ="  + "girls");
        Object girls1 = redisTemplate.opsForList().rightPop("girls");
        System.out.println("girls1 = " + girls1);
        Object girls2 = redisTemplate.opsForList().rightPop("girls");
        System.out.println("girls2 = " + girls2);
        Object girls3 = redisTemplate.opsForList().rightPop("girls");
        System.out.println("girls3 = " + girls3);

    }
    @Test
    public void testSet(){
        //添加数据：sadd key value1 value2 value3....
        redisTemplate.opsForSet().add("girs","昭武帝","赵无敌","赵武帝");
        //获取集合中元素的数量 scard key
        Long girs = redisTemplate.opsForSet().size("girs");
        System.out.println("总数量 = " + girs);
        //判断value是否在集合存在  sismember key value
        Boolean member = redisTemplate.opsForSet().isMember("girs", "赵武帝");
        System.out.println("赵武帝是否在集合内 = " + member);


    }
    @Test
    public void testZset(){
        //添加数据zadd key 分值1 成员1 分值2 成员2 ...
        redisTemplate.opsForZSet().add("game:rank", "张三", 200);
        redisTemplate.opsForZSet().add("game:rank", "李四", 20);
        redisTemplate.opsForZSet().add("game:rank", "王五", 150);
        redisTemplate.opsForZSet().add("game:rank", "赵六", 290);

        //zrange key 起始索引 结束索引
        Set<Object> range = redisTemplate.opsForZSet().range("game:rank", 0, -1);
        System.out.println("range = " + range);

        //zincrby key 增加的分值 成员
        redisTemplate.opsForZSet().incrementScore("game:rank", "王五", 100);

        range = redisTemplate.opsForZSet().range("game:rank", 0, -1);
        System.out.println("range = " + range);
    }


}
