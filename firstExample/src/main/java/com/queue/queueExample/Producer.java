package com.queue.queueExample;

import redis.clients.jedis.Jedis;

public class Producer {

    final Jedis jedis ;
    final String key;

    public Producer(Jedis jedis, String key) {
        this.jedis = jedis;
        this.key = key;
    }

    public void produce(String msg){
        Long l = jedis.lpush(key,msg);
        System.out.println("producer:"+Thread.currentThread().getName()+":"+msg+"   "+l);
    }

}
