package com.coderman.concurrent.publish;

import com.coderman.concurrent.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 不安全的发布对象方式
 * @Author zhangyukang
 * @Date 2021/1/23 17:15
 * @Version 1.0
 **/
@NotThreadSafe
@Slf4j
public class UnSafePublishObject {
    private String[] container= {"a","b","c","d"};

    public String[] getContainer(){
        return container;
    }

    public static void main(String[] args) throws InterruptedException {
        UnSafePublishObject u=new UnSafePublishObject();
        String[] container = u.getContainer();
        log.info("container:{}",Arrays.asList(container));
        //此处模拟有其他线程修改
        new Thread(()->{
            String[] container1 = u.getContainer();
            container1[0]="modified";
        }).start();
        TimeUnit.SECONDS.sleep(1);
        log.info("container:{}",Arrays.asList(container));
    }
}
