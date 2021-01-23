package com.coderman.concurrent.example;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式线程安全的发布对象
 * @Author zhangyukang
 * @Date 2021/1/23 17:32
 * @Version 1.0
 **/
@ThreadSafe
@Slf4j
public class SingletonExample1 {

    private static final SingletonExample1 instance=new SingletonExample1();

    private SingletonExample1(){}

    public static SingletonExample1 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 2000; i++) {
            executorService.submit(()->{
                SingletonExample1 instance = SingletonExample1.getInstance();
                log.info(String.valueOf(instance.hashCode()));
            });
        }
        executorService.shutdown();
    }
}
