package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author zhangyukang
 * @Date 2021/1/26 20:36
 * @Version 1.0
 **/
@Slf4j
public class FutureCallableExample8 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Future<String> future = pool.submit(() -> {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                log.info("do something in callable ....");
            }
            return "hello world";
        });
        log.info("main.......");
        String result = future.get();//阻塞
        log.info("result:{}",result);
    }
}
