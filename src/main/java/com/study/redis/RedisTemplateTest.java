package com.study.redis;

import com.alibaba.fastjson.JSON;
import com.study.redis.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTemplateTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis_old.xml");
        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        User user = new User();
        user.setName("zhangsan");
        user.setAge(19);
        valueOperations.set(user , user);
        User getUser = (User)valueOperations.get(user);
        System.out.println(JSON.toJSONString(getUser));
    }
}
