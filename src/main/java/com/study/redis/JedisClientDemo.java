package com.study.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**

 */
public class JedisClientDemo {

    public static void main(String[] args) {
        //sentinel
        //HostAndPort hostAndPort=new HostAndPort();
        //哨兵集群的地址
        //JedisSentinelPool jedisSentinelPool=new JedisSentinelPool();
        JedisPool jedisPool = null;
        //jedisPool.getResource().set
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(new HostAndPort("192.168.11.153", 7000));
        hostAndPortSet.add(new HostAndPort("192.168.11.153", 7001));

        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet);
        jedisCluster.set("", "");
        String projectId = "123";
        jedisCluster.set("vote:round" + projectId,"1");
        jedisCluster.incr("vote:round:" + projectId);

        String voteRound = jedisCluster.get("vote:round:"+ projectId);

        String expertId= "expertId";

        jedisCluster.zadd("vote:" + projectId+":" + voteRound, 1, "张三");
        jedisCluster.zadd("vote:" + projectId+":" + voteRound, 1, "李四");
        jedisCluster.zadd("vote:" + projectId+":" + voteRound, 1, "王五");
        jedisCluster.zadd("vote:" + projectId+":" + voteRound, 1, "王五");

        Long vote = jedisCluster.zunionstore("vote:" + projectId + ":" + voteRound, "张三");
    }
}
