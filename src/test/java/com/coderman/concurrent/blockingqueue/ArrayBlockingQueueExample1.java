package com.coderman.concurrent.blockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列的常用方法
 *
 * @Author zhangyukang
 * @Date 2021/1/29 09:58
 * @Version 1.0
 **/
@Slf4j
public class ArrayBlockingQueueExample1 {

    private static final int QUEUE_SIZE = 10;

    private static final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    /**********************************offer and poll test*******************************/

    /**
     * 当队列满的时候返回false
     * @param item
     */
    public static void testOffer(String item) {
        boolean offer = blockingQueue.offer(item);
        log.info("offer item {},{}",item,offer);
    }

    /**
     * 当队列为空的时候返回null
     */
    public static void testPoll() {
        String item = blockingQueue.poll();
        log.info("poll item {}",item);
    }


    /**********************************add and  remove test*******************************/



    /**
     * 当队列满的时候抛出异常: Exception in thread "main" java.lang.IllegalStateException: Queue full
     * @param item
     */
    public static void testAdd(String item) {
        log.info("add item {}",item);
        blockingQueue.add(item);
    }

    /**
     * 当队列为空的时候抛出异常: Exception in thread "main" java.util.NoSuchElementException
     */
    public static void testRemove() {
        String item = blockingQueue.remove();
        log.info("remove item {}",item);
    }




    /**********************************put and take test*******************************/

    /**
     * 阻塞方法:当队列满的时候阻塞。
     * @param item
     * @throws InterruptedException
     */
    public static void testPut(String item) throws InterruptedException {
        log.info("put item {}",item);
        blockingQueue.put(item);
    }

    /**
     * 阻塞方法:当队列空的时候阻塞。
     * @throws InterruptedException
     */
    public static void testTake() throws InterruptedException {
        String item = blockingQueue.take();
        log.info("take item {}",item);
    }
}
