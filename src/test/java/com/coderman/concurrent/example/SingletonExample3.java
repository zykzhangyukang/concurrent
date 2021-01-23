package com.coderman.concurrent.example;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhangyukang
 * @Date 2021/1/23 17:43
 * @Version 1.0
 **/
@ThreadSafe
@Slf4j
public class SingletonExample3 {
    private static volatile SingletonExample3 instance = null;

    private SingletonExample3() {

    }

    public static SingletonExample3 getInstance() {
        if(instance==null){
            synchronized (SingletonExample3.class){
                if(instance==null){
                    instance=new SingletonExample3();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Set<String> set=new HashSet<>();
        for (int i = 0; i < 200000; i++) {
            executorService.submit(()->{
                SingletonExample3 instance = SingletonExample3.getInstance();
                String hashcode = String.valueOf(instance.hashCode());
                set.add(hashcode);
            });
        }
        executorService.shutdown();
        log.info("size:{}",set.size());
    }

}
