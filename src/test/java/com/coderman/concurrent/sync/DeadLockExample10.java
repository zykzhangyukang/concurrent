package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 发现死锁的方式: JPS 查看进程号+ JStack 打印栈信息
 * @Author zhangyukang
 * @Date 2021/1/27 10:56
 * @Version 1.0
 **/
@Slf4j
public class DeadLockExample10 {
    public static void main(String[] args) {
        Lock lock1=new ReentrantLock();
        Lock lock2=new ReentrantLock();
        new Thread(()->{
            lock1.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info("{} get lock1....",Thread.currentThread().getName());
                lock2.lock();
                try {
                    log.info("{} do something.....",Thread.currentThread().getName());
                }finally {
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        },"thread1").start();

        new Thread(()->{
            lock2.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info("{} get lock2....",Thread.currentThread().getName());
                lock1.lock();
                try {
                    log.info("{} do something.....",Thread.currentThread().getName());
                }finally {
                    lock1.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
            }
        },"thread2").start();
    }
}
