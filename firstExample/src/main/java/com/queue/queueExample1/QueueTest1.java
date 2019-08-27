package com.queue.queueExample1;

public class QueueTest1 {
    public static void main(String[] args) {


        MessageProducer messageProducer = new MessageProducer();
        Thread[] threads = new Thread[5];
        for (int i = 0;i<threads.length;i++){
            threads[i] = new Thread(messageProducer,"T"+i);
        }
        for (int i = 0;i<threads.length;i++){
            threads[i].start();
        }

        MessageConsumer messageConsumer = new MessageConsumer();
        Thread t1 = new Thread(messageConsumer, "thread6");
        Thread t2 = new Thread(messageConsumer, "thread7");
        t1.start();
        t2.start();
    }
}
