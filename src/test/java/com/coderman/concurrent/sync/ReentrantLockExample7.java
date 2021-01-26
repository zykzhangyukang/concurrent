package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangyukang
 * @Date 2021/1/26 11:35
 * @Version 1.0
 **/
@Slf4j
public class ReentrantLockExample7 {

    private static final ReentrantLock lock=new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                method1();
            }
        },"thread-1").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                method1();
            }
        },"thread-2").start();
    }

    public static void method1() {
        lock.lock();
        try {
            log.info("{} do method1 something",Thread.currentThread().getName());
            method2();//可重入
        }finally {
            lock.unlock();
        }
    }

    public static void method2(){
        lock.lock();
        try {
            log.info("{} do method2 something",Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }
}
