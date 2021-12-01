package com.study.designmode.single;

public class SingleTest {
    private static SingleTest single = null;

    private SingleTest() {

    }

    public static SingleTest getSingleTest() {
        if (single == null) {
            synchronized (single) {
                if (single == null) {
                    single = new SingleTest();
                }
            }
        }
        return single;
    }

}
