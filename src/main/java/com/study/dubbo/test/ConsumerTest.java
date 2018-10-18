package com.study.dubbo.test;

import com.study.dubbo.provider.DubboProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ConsumerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring-dubbo_consumer.xml" });

        DubboProviderService demoService = (DubboProviderService) context.getBean("dubboService");
        String hello = demoService.sayHello("hejingyuan");
        System.out.println(hello);

        List list = demoService.getUsers();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
        System.in.read();
    }
}
