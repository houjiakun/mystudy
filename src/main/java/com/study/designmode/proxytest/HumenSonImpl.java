package com.study.designmode.proxytest;

import java.io.Serializable;

public class HumenSonImpl implements Humen,Serializable{
    @Override
    public String run() {
        System.out.println("baby run");
        return null;
    }

    @Override
    public String eat() {
        System.out.println("baby eat");
        return null;
    }
}
