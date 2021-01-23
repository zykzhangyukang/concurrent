package com.coderman.concurrent.example;

import com.coderman.concurrent.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程不安全的单例
 * @Author zhangyukang
 * @Date 2021/1/23 17:36
 * @Version 1.0
 **/
@NotThreadSafe
@Slf4j
public class SingletonExample2 {
    private static  SingletonExample2 instance=null;

    private SingletonExample2(){}

    public static SingletonExample2 getInstance(){
        if(instance==null){
            instance=new SingletonExample2();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Set<String> set=new HashSet<>();
        for (int i = 0; i < 200000; i++) {
            executorService.submit(()->{
                SingletonExample2 instance = SingletonExample2.getInstance();
                String hashcode = String.valueOf(instance.hashCode());
                set.add(hashcode);
            });
        }
        executorService.shutdown();
        log.info("size:{}",set.size());
    }
}
