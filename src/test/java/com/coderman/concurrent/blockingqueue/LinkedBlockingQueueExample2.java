package com.coderman.concurrent.blockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2021/1/29 10:19
 * @Version 1.0
 **/
@Slf4j
public class LinkedBlockingQueueExample2 {

    private static final LinkedBlockingQueue<String> LINKED_BLOCKING_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        LINKED_BLOCKING_QUEUE.put(String.valueOf(i));
                        log.info("{} put item {}", Thread.currentThread().getName(), i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者:" + j).start();
        }

        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        String take = LINKED_BLOCKING_QUEUE.take();
                        log.info("{} get item {}", Thread.currentThread().getName(), take);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者:" + j).start();
        }
    }
}
