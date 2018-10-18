package com.study.mongodb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MongodbSpringTest {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.test();
        userRepository.createCollection();
        MongodbUser user = new MongodbUser();
        user.setObjectId(UUID.randomUUID().toString());
        user.setName("jack");
        user.setCreateDate(new Date());
        userRepository.insert(user);
        List<MongodbUser> jack = userRepository.findListByName("jack");
    }

}
