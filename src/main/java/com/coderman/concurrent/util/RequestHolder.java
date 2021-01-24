package com.coderman.concurrent.util;

/**
 * 请求上下文持有者
 * @Author zhangyukang
 * @Date 2021/1/24 11:07
 * @Version 1.0
 **/
public class RequestHolder {

    private final static ThreadLocal<Long> userId =new ThreadLocal<>();

    public static void add(Long id){
        userId.set(id);
    }

    public static Long get(){
        return userId.get();
    }

    public static void remove(){
        userId.remove();
    }
}
