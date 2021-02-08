package com.coderman.concurrent;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 11:42
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScans(
        @MapperScan(value = {"com.coderman.concurrent.mapper"})
)
public class ConcurrentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConcurrentApplication.class,args);
    }
}
