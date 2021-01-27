package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池实习生产者消费者
 *
 * @Author zhangyukang
 * @Date 2021/1/27 11:33
 * @Version 1.0
 **/
@Slf4j
public class ProducerAndConsumerExample13 {
    public static void main(String[] args) {
        final int MAX_SIZE = 2;
        ExecutorService prouderPool = Executors.newFixedThreadPool(10);
        ExecutorService consumerPool = Executors.newFixedThreadPool(10);
        List<String> container = new ArrayList<>();

        //1000个生产任务
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            prouderPool.submit(() -> {
                synchronized (ProducerAndConsumerExample13.class) {
                    while (container.size() == MAX_SIZE) {
                        try {
                            ProducerAndConsumerExample13.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String item = String.valueOf(finalI);
                    container.add(item);
                    ProducerAndConsumerExample13.class.notifyAll();
                    log.info("{} get item {} size:{}", Thread.currentThread().getName(), item, container.size());
                }
            });
        }
        //1000个消费任务
        for (int i = 0; i < 1000; i++) {
            consumerPool.submit(() -> {
                synchronized (ProducerAndConsumerExample13.class) {
                    while (container.size() == 0) {
                        try {
                            ProducerAndConsumerExample13.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String item = container.remove(0);
                    ProducerAndConsumerExample13.class.notifyAll();
                    log.info("{} add item {} size:{}", Thread.currentThread().getName(), item, container.size());
                }
            });
        }


    }
}
