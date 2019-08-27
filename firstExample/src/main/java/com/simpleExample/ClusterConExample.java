package com.simpleExample;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class ClusterConExample {

    public static Jedis getConnection(){
//        Jedis jedis = new Jedis("localhost",6379,5000);
//
//        System.out.println(jedis.configGet("m*"));
        //创建连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(50);
        config.setMinIdle(0);
        config.setMaxWaitMillis(5000);
        int timeOut = 5000;
        JedisPool jedispool = new JedisPool(config,"localhost",6379,timeOut);
        //从连接池中获取一个连接
        Jedis jedis = jedispool.getResource();
        return jedis;
    }

    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("localhost", 6379));
//        nodes.add(new HostAndPort("localhost", 6380));
//        nodes.add(new HostAndPort(URL, 6381));
//        nodes.add(new HostAndPort(URL, 6382));
//        nodes.add(new HostAndPort(URL, 6383));
//        nodes.add(new HostAndPort(URL, 6384));
        // 单例
//        JedisCluster cluster = new JedisCluster(nodes);
//        cluster.set("cluster", "hello world");


        Jedis jedis = getConnection();
        String value = jedis.get("a");
        System.out.println(value);


        Long bitCount = jedis.bitcount("a");
        System.out.println(bitCount);


    }
}
