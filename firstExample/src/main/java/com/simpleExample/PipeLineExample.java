package com.simpleExample;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PipeLineExample {
    public static void main(String[] args) {
        Jedis jedis = ClusterConExample.getConnection();
        Pipeline pipeline = jedis.pipelined();
        pipeline.append("a","1231");
        pipeline.append("b","1231");
        pipeline.set("a","aaa");
        pipeline.get("a");
        pipeline.exists("c");

        List list = pipeline.syncAndReturnAll();
        System.out.println(Arrays.toString(list.toArray()));
    }
}
