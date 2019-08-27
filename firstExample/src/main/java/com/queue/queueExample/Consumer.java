package com.queue.queueExample;

import redis.clients.jedis.Jedis;

import java.util.List;

public class Consumer {
    final Jedis jedis ;
    final String key;

    public Consumer(Jedis jedis, String key) {
        this.jedis = jedis;
        this.key = key;
    }

    public void consumer() throws InterruptedException {
        while(true){


            System.out.println("Waiting for a message in the queue");
            List<String> messages = jedis.blpop(0,key);

            System.out.println("Got the message KEY:" + messages.get(0) + " VALUE:" + messages.get(1));
            String payload = messages.get(1);
            System.out.println("Message received:" + payload);

        }

    }

}
