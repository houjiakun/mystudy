package com.study.shopstrategy;

public class WeChatPay implements Pay {
    @Override
    public void pay(String id) {
        System.out.println("WeChatPay" + id);
    }
}
