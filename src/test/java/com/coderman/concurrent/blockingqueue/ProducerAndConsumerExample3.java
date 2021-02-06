package com.coderman.concurrent.blockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 使用阻塞队列实现生产者消费者
 * @Author zhangyukang
 * @Date 2021/1/29 10:28
 * @Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class ProducerAndConsumerExample3 {

    private static final BlockingQueue<String> queue= new  ArrayBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService producerPool = Executors.newFixedThreadPool(1);
        ExecutorService consumerPool = Executors.newFixedThreadPool(1);

        //提交1000个生产任务给线程池
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            producerPool.submit(()->{
                try {
                    queue.put(String.valueOf(finalI));
                    log.info("生产者{}-------->{} size:{}",Thread.currentThread().getId(),finalI,queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                queue.put("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (true){
            String take = queue.take();
            if("end".equals(take)){
               break;
            }
            consumerPool.submit(()->{
                log.info("消费者{}-------->{} size:{}",Thread.currentThread().getId(),take,queue.size());
            });
        }

        producerPool.shutdown();
        consumerPool.shutdown();
    }
}
