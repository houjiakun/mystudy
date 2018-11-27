package com.study.designmode.shopstrategy;

public class Test {
    public static void main(String[] args) {
        Order order = new Order("1", PayType.WECHAT_PAY.getPay());
        order.paying();
    }
}
