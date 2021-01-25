package com.coderman.concurrent.container;

import com.coderman.concurrent.annoation.NotThreadSafe;
import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author zhangyukang
 * @Date 2021/1/25 10:05
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class CollectionsMapExample9 {

    private static int clientTotal=5000;
    private static int threadTotal=200;

    private static final Map<Integer,Integer> map = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count=i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    doSomething(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("size:{}", map.size());
        executorService.shutdown();
    }

    public static void doSomething(int count){
        map.put(count,count);
    }

}
