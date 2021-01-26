package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2021/1/25 11:18
 * @Version 1.0
 **/
@Slf4j
public class SemaphoreExample5 {

    private static final Semaphore SEMAPHORE=new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(()->{
                try {
                    SEMAPHORE.acquire();
                    TimeUnit.SECONDS.sleep(1);
                    log.info("do something.......");
                    SEMAPHORE.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
