package com.study.adapter;

/**
 * 适配
 */
public class LoginAdapter extends LoginImpl {

    public Boolean QQLogin(String userName, String password){
        System.out.println(String.format("初始化账号:%s,密码:%s", userName, password));
        return super.login(userName, password);
    }
}
