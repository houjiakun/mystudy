package com.study.designmode.shopstrategy;

public class AliPay implements Pay{
    @Override
    public void pay(String id) {
        System.out.println("AliPay" + id);
    }
}
