package com.coderman.concurrent.threadpool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2021/2/7 11:04
 * @Version 1.0
 **/
public class ThreadPoolExample5 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("do something.....");
        }, 1, 2, TimeUnit.SECONDS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("do something in timer....");
            }
        }, 1L, 2000L);
    }
}
