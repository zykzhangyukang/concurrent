package com.coderman.concurrent.unsafeclass;

import com.coderman.concurrent.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhangyukang
 * @Date 2021/1/24 17:43
 * @Version 1.0
 **/
@NotThreadSafe
@Slf4j
public class UnSafeStringExample1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
//        StringBuffer str=new StringBuffer();
        StringBuilder str = new StringBuilder();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            pool.execute(() -> {
                str.append("a");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("length:{}", str.length());
        pool.shutdown();
    }
}
