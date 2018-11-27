package com.study.designmode.single;

public class Hungry {

    private static final Hungry hungry=new Hungry();

    private Hungry(){

    }

    public static Hungry getHungry() {
        System.out.println(System.currentTimeMillis() + "" + hungry);
        return hungry;
    }
}
