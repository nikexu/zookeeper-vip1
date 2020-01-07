package com.xunike.client.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author xunike
 * @Description:
 * @date 2020/1/7 15:58
 * @Version 1.0.0
 */
public class ZookeeperClientTest {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 这是一个默认的watch
        ZooKeeper clinet = new ZooKeeper("101.132.72.22:2182", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("这是一个默认的连接时候的事件："+ event);
            }
        });


        Thread.sleep(1000);

        //创建节点的API
        String dataStr = new String("111");
        clinet.create("/testNode",dataStr.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);


        Stat stat = new Stat();

//        ************************************************************

//        byte[] data = clinet.getData("/data", new Watcher() {
            /**
             * supreme 原生的客户端有个问题
             * 就是接受zk修改数据的消息只会监听一次，下次就监听不到这个节点的数据改动了
             */
//            @Override
//            public void process(WatchedEvent event) {
//                if (Event.EventType.NodeDataChanged.equals(event.getType())){
//                    System.out.println("获取节点数据改变的事件");
//                }
//                System.out.println("获取内容时候的监听器注册" + event);
//            }
//        }, stat);

//        String str = new String(data);
//        System.out.println("获取到的内容"+str);

//        ************************************************************

        /**
         * supreme 当 watch 设置为true的时候，使用的就是默认的那个监听器，就是连接的时候那个监听器
         * 若为 false 就是不设置监听器
         *
         * DataCallback 是个回调的匿名内部类
         *
         */
//        clinet.getData("/data", true, new AsyncCallback.DataCallback() {
//            @Override
//            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
//                System.out.println("回调机制");
//            }
//        },null);



        System.in.read();
    }


}
