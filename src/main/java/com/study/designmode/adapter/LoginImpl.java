package com.study.designmode.adapter;

public class LoginImpl implements Login {
    @Override
    public Boolean login(String userName, String password) {
        System.out.println(userName + "普通登录！");
        return true;
    }
}
