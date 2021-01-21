package com.coderman.concurrent.sync;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 13:27
 * @Version 1.0
 **/
public class SynchronizedExample1 {

    public static synchronized  void test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" i=" + i);
            //可重入
            //method();
        }
    }

    public static synchronized void method(){
        System.out.println("method execution");
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        new Thread(()->{
            example1.test1();
        },"thread-1").start();
        new Thread(()->{
            example2.test1();
        },"thread-2").start();
    }
}
