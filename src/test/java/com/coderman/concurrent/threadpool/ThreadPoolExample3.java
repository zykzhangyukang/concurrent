package com.coderman.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程池的几种类型
 *
 * @Author zhangyukang
 * @Date 2021/2/6 13:48
 * @Version 1.0
 **/
@Slf4j
public class ThreadPoolExample3 {

    private static CountDownLatch countDownLatch=new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        //固定大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        //可缓存的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //单线程的线程池
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();


        for (int i = 0; i < 100; i++) {
            int finalI = i;
            singleThreadPool.submit(() -> {
                doSomething(String.valueOf(finalI));
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        log.info("finished......");
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        singleThreadPool.shutdown();
    }

    public static void doSomething(String item) {
        log.info("{} do something ....{}", Thread.currentThread().getName(), item);
    }
}
