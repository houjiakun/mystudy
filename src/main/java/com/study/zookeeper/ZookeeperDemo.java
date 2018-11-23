package com.study.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class ZookeeperDemo {


    public static void main(String[] args) throws Exception{
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.171.128:2181,192.168.171.128:2182,192.168.171.128:2183",
                4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(JSON.toJSONString(watchedEvent));
                if(Objects.equals(watchedEvent.getState(), Event.KeeperState.SyncConnected)){
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        watcherDemo(zooKeeper);
    }

    public static void crud(  ZooKeeper zooKeeper ) throws Exception{

        String javaObj = zooKeeper.create("/javaObj11", "javaObjVal".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("新增节点" + javaObj);
        Stat stat = new Stat();
        //查询
        byte[] data = zooKeeper.getData("/javaObj11", true, stat);
        System.out.println(new String (data));
        //修改
        zooKeeper.setData("/javaObj11", "javaObjValNew".getBytes(), stat.getVersion());
        Stat stat1 = new Stat();
        //查询
        byte[] data1 = zooKeeper.getData("/javaObj11", true, stat1);
        System.out.println(new String (data1));
        System.out.println(JSON.toJSONString(stat1));
    }

    public static void watcherDemo(final ZooKeeper zooKeeper ) throws Exception {
        zooKeeper.exists("/javaObj", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("myWatcher->" + JSON.toJSONString(watchedEvent));
            }
        });
        Stat stat =new Stat();
        byte[] data = zooKeeper.getData("/javaObj", true, stat);
        zooKeeper.setData("/javaObj", "1".getBytes(), stat.getVersion());
        System.out.println(new String(data));
        Stat stat1 =new Stat();
         zooKeeper.getData("/javaObj", true, stat1);
        zooKeeper.delete("/javaObj",stat1.getVersion());
    }
}
