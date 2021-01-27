package com.coderman.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhangyukang
 * @Date 2021/1/26 21:03
 * @Version 1.0
 **/
@Slf4j
public class ThreadPoolExample1 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 1000; i++) {
            pool.submit(new MyTask());
        }
    }
}

@Slf4j
class MyTask implements Runnable{

    @Override
    public void run() {
      log.info("{} run .....",Thread.currentThread().getName());
    }
}
