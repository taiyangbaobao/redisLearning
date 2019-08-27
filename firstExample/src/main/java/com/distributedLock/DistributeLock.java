package com.distributedLock;

import com.simpleExample.ClusterConExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public class DistributeLock {
    private final Jedis jedis;

    public DistributeLock() {
        this.jedis = ClusterConExample.getConnection();
    }

    public String lockWithTimeOut(String lockName,long acquireTimeOut,long timeOut){

        //获取时间没有超时，一直获取锁
        long end = System.currentTimeMillis()+acquireTimeOut;
        int lockExpire = (int)(timeOut/1000);
        String identifier = UUID.randomUUID().toString();
        String retIdentifier = null;

        try {
            while (System.currentTimeMillis()<end){
                System.out.println(Thread.currentThread().getName()+"，等待锁：");
                if(jedis.setnx(lockName,identifier) == 1){
                    jedis.expire(lockName,lockExpire);
                    retIdentifier = identifier;
                    System.out.println(Thread.currentThread().getName()+"，获取了锁："+System.currentTimeMillis());

                    return retIdentifier;
                }
                if(jedis.ttl(lockName) == -1){
                    jedis.expire(lockName,lockExpire);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }


        return retIdentifier;
    }


    public boolean releaseLock(String lockName,String identifier){
        boolean retFlag = false;
        while (true){
            jedis.watch(lockName);
            if(identifier.equals(jedis.get(lockName))){
                Transaction transaction = jedis.multi();
                transaction.del(lockName);
                List<Object> results = transaction.exec();
                if(results == null){
                    continue;
                }
                System.out.println(Thread.currentThread().getName()+"，解锁了："+identifier);
                retFlag = true;
            }
            jedis.unwatch();
            break;
        }
        return retFlag;
    }


}
