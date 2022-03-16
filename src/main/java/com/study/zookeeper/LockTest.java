package com.study.zookeeper;

import org.I0Itec.zkclient.ZkClient;

public class LockTest {
    public static void main(String[] args) throws Exception {
        ZkClient zkClient = new ZkClient("192.168.111.128:2181", 3000);
        SimpleDistributedLock simple = new SimpleDistributedLock(zkClient, "/locker");
        Thread thread = new Thread(() -> {
            try {
                simple.acquire();
                Thread.sleep(100000);
                System.out.println("thread正在进行运算操作：" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    simple.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("=================\r\n");
            }
        });
        thread.start();
        Thread.sleep(10000);
        Thread thread1 = new Thread(() -> {
            try {
                simple.acquire();
                Thread.sleep(120000);
                System.out.println("thread1正在进行运算操作：" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    simple.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("=================\r\n");
            }
        });
        thread1.start();
        Thread.sleep(1000);
        Thread thread2 = new Thread(() -> {
            try {
                simple.acquire();
                Thread.sleep(120000);
                System.out.println("thread2正在进行运算操作：" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    simple.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("=================\r\n");
            }
        });
        thread2.start();

    }
}