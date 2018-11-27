package com.study.concurrent;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println(i);
        },"interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //设置interrupt标识为true
        System.out.println(thread.isInterrupted());
    }


}
