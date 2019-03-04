package redis;

import com.alibaba.fastjson.JSON;
import com.study.redis.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        User user = new User();
        user.setName("hjk");
        user.setAge(18);
        valueOperations.set(user , user);
        User getUser = (User)valueOperations.get(user);
        System.out.println(JSON.toJSONString(getUser));
    }

    @Test
    public void test2() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("secound" , "hello everyone");
        System.out.println(stringStringValueOperations.get("secound"));
    }
}
