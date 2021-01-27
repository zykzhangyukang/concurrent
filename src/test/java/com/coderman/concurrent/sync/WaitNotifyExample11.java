package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2021/1/27 11:06
 * @Version 1.0
 **/
@Slf4j
public class WaitNotifyExample11 {
    public static void main(String[] args) throws InterruptedException {
        Object lock=new Object();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                synchronized (lock){
                    //并不会释放锁，而是唤醒所用当前在lock上wait的线程,下方的代码执行完成才会释放锁
                    //notify必须要在同步代码块里面调用。
                    lock.notifyAll();
                    for (int i = 0; i < 5; i++) {
                        TimeUnit.SECONDS.sleep(1);
                        log.info("{} do something.....",Thread.currentThread().getName());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        synchronized (lock){
            lock.wait();
            log.info("{} do something.......",Thread.currentThread().getName());
        }

    }
}
