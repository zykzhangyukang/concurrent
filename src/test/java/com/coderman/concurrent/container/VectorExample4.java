package com.coderman.concurrent.container;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author zhangyukang
 * @Date 2021/1/25 09:58
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class VectorExample4 {

    private static int clientTotal=5000;
    private static int threadTotal=200;

    private static final List<String> list=new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    doSomething();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("size:{}",list.size());
        executorService.shutdown();
    }

    public static void doSomething(){
        list.add("a");
    }
}
