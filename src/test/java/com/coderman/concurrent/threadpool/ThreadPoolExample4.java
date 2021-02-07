package com.coderman.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务的线程池
 *
 * @Author zhangyukang
 * @Date 2021/2/6 14:00
 * @Version 1.0
 **/
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        threadPool.schedule(() -> {
            log.info("{} do something", Thread.currentThread().getName());
        }, 10, TimeUnit.SECONDS);
    }
}
