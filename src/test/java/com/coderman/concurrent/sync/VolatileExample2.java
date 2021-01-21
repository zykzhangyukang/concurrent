package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2021/1/21 11:34
 * @Version 1.0
 **/
@Slf4j
public class VolatileExample2 {

    private volatile static boolean init=false;

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                init=true;
                log.info("init status modified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            while (true){
                if(init){
                    log.info("finished....");
                    break;
                }
            }
        }).start();
    }
}
