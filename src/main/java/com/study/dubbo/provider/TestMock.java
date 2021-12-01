package com.study.dubbo.provider;

import java.util.List;

public class TestMock implements DubboProviderService {
    @Override
    public String sayHello(String name) {
        return "hello mock";
    }

    @Override
    public List getUsers() {
        return null;
    }

    public static void main(String[] args) {
        DubboProviderServiceImpl.runWithRefer();
    }
}
