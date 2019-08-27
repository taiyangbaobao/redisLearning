package com.distributedLock;

public class DistributeLockTest {
    public static void main(String[] args) {
        DistributeLock distributeLock = new DistributeLock();
        Thread thread = new Thread(()->distributeLock.lockWithTimeOut("lock",3000,100));
        for (int i = 0;i<10;i++){
            Thread thread1 = new Thread(thread,"T"+i);
            thread1.start();
        }
    }
}
