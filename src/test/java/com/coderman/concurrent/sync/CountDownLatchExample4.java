package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhangyukang
 * @Date 2021/1/25 10:31
 * @Version 1.0
 **/
@Slf4j
public class CountDownLatchExample4 {

    private static final CountDownLatch c=new CountDownLatch(20);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(()->{
                log.info(Thread.currentThread().getName()+" do something.......");
                c.countDown();
            });
        }
        c.await();
        log.info("finished.......");
    }
}
