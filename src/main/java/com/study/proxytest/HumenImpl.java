package com.study.proxytest;

public class HumenImpl implements  Humen {

    @Override
    public String run() {
        System.out.println("run");
        return "run";
    }

    @Override
    public String eat() {
        return null;
    }

}
