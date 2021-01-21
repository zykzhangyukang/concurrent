package com.coderman.concurrent.atomic;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:43
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ConcurrentExample7 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch=new CountDownLatch(1);
        AtomicStampedReference<Long> atomicReference=new AtomicStampedReference<>(0L,0);

        //模拟在此之前有其他线程发送ABA问题 (0->5->0)
        new Thread(()->{
            atomicReference.compareAndSet(0L, 5L, 0, 1);
            atomicReference.compareAndSet(0L, 0L, 1, 2);
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"修改了 0->5->0");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }).start();

        countDownLatch.await();

        boolean success = atomicReference.compareAndSet(0L, 1000L, 0, 2);
        System.out.println("success:"+success);
    }
}
