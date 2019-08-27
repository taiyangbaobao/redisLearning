package com.queue.queueExample1;

import com.simpleExample.ClusterConExample;
import redis.clients.jedis.Jedis;

public class MessageProducer extends Thread {
    public static final String KEY = "message:queue";
    private volatile int count;

    public void putMessage(String message){
        Jedis jedis = ClusterConExample.getConnection();
        Long size = jedis.lpush(KEY,message);
        System.out.println(Thread.currentThread().getName()+" put message:"+message+" ,size:"+size);
        count++;
    }


    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            putMessage("message"+count);
        }
    }

}
