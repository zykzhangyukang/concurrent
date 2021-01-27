package com.coderman.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者与消费者
 *
 * @Author zhangyukang
 * @Date 2021/1/27 11:16
 * @Version 1.0
 **/
public class ProducerAndConsumerExample12 {
    public static void main(String[] args) {
        MyContainer myContainer = new MyContainer();
        for (int j = 0; j < 5; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        myContainer.put(String.valueOf(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者:"+j).start();
        }

        for (int j = 0; j < 5; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        String s = myContainer.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者:"+j).start();
        }
    }
}

@Slf4j
class MyContainer {
    private static final List<String> list = new ArrayList<>();

    private static final int MAX_SIZE = 10;

    public void put(String item) throws InterruptedException {
        synchronized (MyContainer.class) {
            while (list.size() == MAX_SIZE) {
                MyContainer.class.wait();
            }
            //TimeUnit.SECONDS.sleep(1);
            list.add(item);
            log.info("{} add item {} ,size:{}", Thread.currentThread().getName(), item, list.size());
            MyContainer.class.notifyAll();
        }
    }

    public String get() throws InterruptedException {
        synchronized (MyContainer.class) {
            while (list.size() == 0) {
                MyContainer.class.wait();
            }
            //TimeUnit.SECONDS.sleep(1);
            String item = list.remove(0);
            log.info("{} get item {} ,size:{}", Thread.currentThread().getName(), item, list.size());
            MyContainer.class.notifyAll();
            return item;
        }
    }
}
