package com.study.dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring-dubbo_provider.xml" });
        context.start();
        System.in.read(); //为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
