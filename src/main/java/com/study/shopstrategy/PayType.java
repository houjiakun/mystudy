package com.study.shopstrategy;

public enum PayType {

    ALI_PAY(new AliPay()),
    WECHAT_PAY(new WeChatPay());

    Pay pay;

    private PayType(Pay pay){
        this.pay=pay;
    }
    public Pay getPay(){
        return this.pay;
    }

}
