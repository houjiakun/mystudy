package com.study.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTool {

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        /*String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);*/

       /* if (LOCK_SUCCESS.equals(result)) {
            return true;
        }*/
        return false;

    }

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i <100; i++) {
            new Thread(()->{
                Jedis jedis = new Jedis("192.168.61.134");
                Boolean res = tryGetDistributedLock(jedis, "userLock",  "1", 10000);
                if(res){
                    System.out.println(Thread.currentThread().getName()+"获取锁成功！");
                    Boolean res1 = releaseDistributedLock(jedis, "userLock",  "1");
                    System.out.println(res1?Thread.currentThread().getName()+"释放锁成功！":Thread.currentThread().getName()+"释放锁失败！");
                }
            }, Integer.toString(i)).start();
        }
    }

}