package com.coderman.concurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * ThreadLocal的使用
 * @Author zhangyukang
 * @Date 2021/1/24 11:21
 * @Version 1.0
 **/
@Slf4j
public class ThreadLocalExample1 {

    private static final ThreadLocal<String> param=new ThreadLocal<>();

    public static void main(String[] args) {
        param.set("10086");
        method1();
        param.remove();
    }

    private static void method3() {
        log.info("method3:{}",param.get());
    }

    private static void method2() {
        log.info("method2:{}",param.get());
        method3();
    }

    private static void method1() {
        log.info("method1:{}",param.get());
        method2();
    }
}
