package com.study.redis.distributedLock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTool {

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * ���Ի�ȡ�ֲ�ʽ��
     * @param jedis Redis�ͻ���
     * @param lockKey ��
     * @param requestId �����ʶ
     * @param expireTime ����ʱ��
     * @return �Ƿ��ȡ�ɹ�
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * �ͷŷֲ�ʽ��
     * @param jedis Redis�ͻ���
     * @param lockKey ��
     * @param requestId �����ʶ
     * @return �Ƿ��ͷųɹ�
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
                    System.out.println(Thread.currentThread().getName()+"��ȡ���ɹ���");
                    Boolean res1 = releaseDistributedLock(jedis, "userLock",  "1");
                    System.out.println(res1?Thread.currentThread().getName()+"�ͷ����ɹ���":Thread.currentThread().getName()+"�ͷ���ʧ�ܣ�");
                }
            }, Integer.toString(i)).start();
        }
    }

}
