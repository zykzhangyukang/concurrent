package com.coderman.concurrent.immutable;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Author zhangyukang
 * @Date 2021/1/24 10:50
 * @Version 1.0
 **/
@Slf4j
public class UnModifiedExample2 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list = Collections.unmodifiableList(list);
        list.add("a");
        list.add("b");
        list.add("c");
        log.info("list:{}",list);

        Map<String,Object> map=new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map = Collections.unmodifiableMap(map);
        map.put("d",4);
        log.info("map:{}",map);
    }
}
