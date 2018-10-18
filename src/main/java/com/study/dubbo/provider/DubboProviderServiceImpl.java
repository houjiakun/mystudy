package com.study.dubbo.provider;


import com.study.dubbo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DubboProviderServiceImpl implements DubboProviderService {
    public String sayHello(String name) {
        return "Hello " + name;
    }

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
}
