package com.coderman.concurrent.atomic;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:43
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ConcurrentExample6 {
    private volatile String name="test";
    public static void main(String[] args) {
        AtomicReferenceFieldUpdater<ConcurrentExample6,String> updater=
                AtomicReferenceFieldUpdater.newUpdater(ConcurrentExample6.class,String.class,"name");
        ConcurrentExample6 example6 = new ConcurrentExample6();
        updater.compareAndSet(example6,"test","hello world");
        System.out.println(example6.name);
    }
}
