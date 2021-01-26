package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author zhangyukang
 * @Date 2021/1/25 12:02
 * @Version 1.0
 **/
@Slf4j
public class CyclicBarrierExample6 {

    private static final CyclicBarrier c=new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.submit(()->{
                try {
                    String name = Thread.currentThread().getName();
                    log.info("thread {} ready.......",name);
                    TimeUnit.SECONDS.sleep(2);
                    c.await();
                    log.info("thread {} over........",name);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
