package com.coderman.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程池的几个核心参数
 * @Author zhangyukang
 * @Date 2021/2/6 11:00
 * @Version 1.0
 **/
@Slf4j
public class ThreadPoolExample2 {

    /**
     * 1. corePoolSize:线程池的常驻线程个数,当线程池存在空闲线程时,不会回收的线程。
     * 2. maximumPoolSize: 线程池所能拥有的最大的线程数。
     * 3. keepAliveTime: 当存在空闲线程时,这些空闲线程能存活的时间。
     * 4. ThreadFactory: 线程工厂,可自定义线程的标识。
     * 5. workQueue: 工作队列,一般设置为阻塞队列。当达到核心线程数时,此时如果没有多余的工作线程,任务将进入工作队列。
     * 6. rejectedExecutionHandler: 当到达最大线程数时,且工作队列已满的时,如果此时还有任务提交,拒绝该任务的策略。
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(
                5,
                10,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> {
                    log.info("new thread....");
                    return new Thread(r);
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 6; i++) {
            pool.submit(() -> {
                log.info("{} do something....", Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
