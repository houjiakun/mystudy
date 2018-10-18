package com.study.dubbo.provider;

import java.util.List;

public interface DubboProviderService {
    String sayHello(String name);

    public List getUsers();
}
