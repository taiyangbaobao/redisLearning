package com.queue.queueExample1;

import com.simpleExample.ClusterConExample;
import redis.clients.jedis.Jedis;

public class MessageConsumer implements Runnable {
    public static final String KEY = "message:queue";
    private volatile int count;

    public void consumeMessage(){
        Jedis jedis = ClusterConExample.getConnection();
        String  message = jedis.lpop(KEY);
        if(message!= null){
            System.out.println(Thread.currentThread().getName()+" consume message:"+message);
            count++;
        }

    }
    @Override
    public void run() {
        while (true){
            consumeMessage();
        }
    }



}
