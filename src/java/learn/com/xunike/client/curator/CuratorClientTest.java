package com.xunike.client.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

/**
 * @author xunike
 * @Description:
 * @date 2020/1/7 23:47
 * @Version 1.0.0
 */
public class CuratorClientTest {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "101.132.72.22:2182",
                //每次间隔1000毫秒重试一次，总共重试3次
                new RetryNTimes(3,1000)
        );
        //启动客户端
        client.start();
        //创建节点
        client.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curatorNode","1".getBytes());

        //这是对节点做缓存
        NodeCache nodeCache = new NodeCache(client,"/curatorNode");
        //监听节点值的改变
        nodeCache.start(true);
        //添加监听器
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("数据修改了");
            }
        });

        System.in.read();
    }
}
