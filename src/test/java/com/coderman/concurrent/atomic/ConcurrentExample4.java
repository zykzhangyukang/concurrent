package com.coderman.concurrent.atomic;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:43
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ConcurrentExample4 {
    private static int clientTotal=5000;
    private static int threadTotal=200;

    private static LongAdder count=new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("count:{}",count.intValue());
        executorService.shutdown();
    }

    public static void add(){
        count.add(1);
    }
}
