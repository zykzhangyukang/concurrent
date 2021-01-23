package com.coderman.concurrent.publish;

import com.coderman.concurrent.annoation.NotRecommend;
import com.coderman.concurrent.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象溢出导致线程不安全
 * @Author zhangyukang
 * @Date 2021/1/23 17:24
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape=0;

    private Escape(){
        new InnerClass();
    }

    private class InnerClass{
        private InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
