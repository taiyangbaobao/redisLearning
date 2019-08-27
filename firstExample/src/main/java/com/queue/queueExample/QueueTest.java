package com.queue.queueExample;

import com.simpleExample.ClusterConExample;
import redis.clients.jedis.Jedis;

public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = ClusterConExample.getConnection();
        String key = "firstQueue";
        Producer producer = new Producer(jedis,key);
        producer.produce("111");

        Consumer consumer = new Consumer(jedis,key);
        consumer.consumer();
        Thread.sleep(1000);
        producer.produce("2222");
        Thread.sleep(1000);
        producer.produce("3333");
    }
}
