package com.xunike.client.zkclinet;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @author xunike
 * @Description:
 * @date 2020/1/7 23:29
 * @Version 1.0.0
 */
public class ZkClientUpdate {

    public static void main(String[] args) {
        ZkClient client = new ZkClient(
                "101.132.72.22:2182",
                1000,
                10000,
                new SerializableSerializer());


        client.writeData("/testNode2",4);



    }
}
