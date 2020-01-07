package com.xunike.client.zkclinet;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;

/**
 * @author xunike
 * @Description:
 * 因为这边要涉及一样的序列化机制，所以要用java 操作一样序列化机制的客户端，而不能使用远程连接
 * @date 2020/1/7 23:06
 * @Version 1.0.0
 */
public class ZkClientTest {

    public static void main(String[] args) throws IOException {
        ZkClient client = new ZkClient(
                "101.132.72.22:2182",
                1000,
                10000,
                new SerializableSerializer());


//        client.createPersistent("/testNode2","2".getBytes());

        /**订阅数据的改变**/
        client.subscribeDataChanges("/testNode2", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("数据被修改，修改的值"+data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("数据被删");
            }
        });

        System.in.read();

    }




}
