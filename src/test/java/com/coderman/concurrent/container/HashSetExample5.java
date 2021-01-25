package com.coderman.concurrent.container;

import com.coderman.concurrent.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
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
@NotThreadSafe
public class HashSetExample5 {

    private static int clientTotal=5000;
    private static int threadTotal=200;

    private static final Set<Integer> set =new HashSet<>();

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
        log.info("size:{}", set.size());
        executorService.shutdown();
    }

    public static void doSomething(int count){
        set.add(count);
    }

}
