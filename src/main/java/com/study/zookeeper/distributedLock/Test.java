package com.study.zookeeper.distributedLock;

public class Test {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        Runnable runnable = ()->{
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock("192.168.61.134:2181", "test1");
                    lock.lock();
                    secskill();
                    System.out.println(Thread.currentThread().getName() + "��������");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}