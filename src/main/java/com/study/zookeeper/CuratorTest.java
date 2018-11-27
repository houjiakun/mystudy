package com.study.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework=CuratorFrameworkFactory.
                builder().connectString("192.168.171.128:2181," +
                "192.168.171.128:2182").
                sessionTimeoutMs(4000).retryPolicy(new
                ExponentialBackoffRetry(1000,3)).
                namespace("").build();

        curatorFramework.start();

        TreeCache cache = new TreeCache(curatorFramework, "/server1");

        TreeCacheListener treeCacheListener=new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("myWathcer->" + JSON.toJSONString(event));
            }
        };
        cache.getListenable().addListener(treeCacheListener);
        cache.start();
        PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("myChildren watcher->" +JSON.toJSONString(pathChildrenCacheEvent));
            }
        };

        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, "/server1", true);
        childrenCache.getListenable().addListener(childrenCacheListener);
        childrenCache.start(PathChildrenCache.StartMode.NORMAL);


        final NodeCache nodeCache=new NodeCache(curatorFramework,"/server1",false);
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node watcher:" + nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();

        Stat stat =new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/server1");
        System.out.println(new String (bytes));
        curatorFramework.setData().withVersion(stat.getVersion()).forPath("/server1", "123".getBytes());

        byte[] bytes1 = curatorFramework.getData().storingStatIn(stat).forPath("/server1");
        System.out.println(new String(bytes1));
        System.in.read();
    }


}
