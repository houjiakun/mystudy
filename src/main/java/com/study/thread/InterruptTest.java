package com.study.thread;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
            while(true){
                boolean isInterrupt = Thread.currentThread().isInterrupted();
                if(isInterrupt){
                    System.out.println("before interrupted -> " + isInterrupt);
                    Thread.interrupted();
                    System.out.println("after interrupted -> " + Thread.currentThread().isInterrupted());
                }

            }
        });
        thread.start();
        thread.interrupt();
    }
}
