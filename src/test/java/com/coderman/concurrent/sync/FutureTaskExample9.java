package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2021/1/26 20:42
 * @Version 1.0
 **/
@Slf4j
public class FutureTaskExample9 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask= new FutureTask<>(() -> {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                log.info("do something in callable.....");
            }
            return "hello world";
        });
        new Thread(futureTask).start();
        log.info("main.......");
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
