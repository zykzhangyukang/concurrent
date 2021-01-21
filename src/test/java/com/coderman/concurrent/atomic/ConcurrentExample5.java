package com.coderman.concurrent.atomic;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:43
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ConcurrentExample5 {
    public static void main(String[] args) {
        AtomicReference<Long> atomicReference=new AtomicReference<>(0L);
        atomicReference.compareAndSet(0L,5L); //5
        atomicReference.compareAndSet(2L,6L); //no
        atomicReference.compareAndSet(5L,4L); //4
        atomicReference.compareAndSet(4L,2L); //2
        System.out.println(atomicReference.get());
    }
}
