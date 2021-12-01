package com.study.dubbo.provider;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.study.dubbo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DubboProviderServiceImpl implements DubboProviderService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public List getUsers() {
        List list = new ArrayList();
        User u1 = new User();
        u1.setName("hejingyuan");
        u1.setAge(20);
        u1.setSex("f");

        User u2 = new User();
        u2.setName("xvshu");
        u2.setAge(21);
        u2.setSex("m");

        list.add(u1);
        list.add(u2);

        return list;
    }


    public static void main(String[] args) throws Exception{
        startWithExport();
    }

    private static void startWithExport() throws InterruptedException {
        // 服务提供者的配置
        ServiceConfig<DubboProviderServiceImpl> service = new ServiceConfig<>();
        service.setInterface(DubboProviderService.class);
        service.setRef(new DubboProviderServiceImpl());
        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://192.168.111.128:2181"));
        // 最重要的入口，将服务暴露出去
        service.export();

        System.out.println("dubbo service started");
        // 卡住主进程
        new CountDownLatch(1).await();
    }

    public static void runWithRefer() {
        ReferenceConfig<DubboProviderService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("dubbo-demo-api-consumer"));
        // 注册中心配置
        reference.setRegistry(new RegistryConfig("zookeeper://192.168.111.128:2181"));
        // 设置接口类
        reference.setInterface(DubboProviderService.class);
        // 关键代码：消费者入口
        DubboProviderService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println(message);
    }

}
