package com.coderman.concurrent.immutable;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * final可以修饰 方法，类，属性
 * @Author zhangyukang
 * @Date 2021/1/24 10:41
 * @Version 1.0
 **/
@Slf4j
public class FinalExample1 {
    private final static int a=1;
    private final static Integer b=2;
    private final static Map<String,Object> MAP=new HashMap<>();

    static {
        MAP.put("a",1);
        MAP.put("b",2);
        MAP.put("c",3);
        MAP.put("d",4);
    }

    public void method(final int a){
        log.info("a={}",a);
        //a=4;
    }

    public static void main(String[] args) {
        //a=2;
        //b=3;
        log.info("map:{}",MAP);
        MAP.put("e",5);
        //MAP=new HashMap<>();
        log.info("map:{}",MAP);
    }
}
