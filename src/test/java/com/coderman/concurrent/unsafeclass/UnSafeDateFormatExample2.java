package com.coderman.concurrent.unsafeclass;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author zhangyukang
 * @Date 2021/1/24 17:43
 * @Version 1.0
 **/
@ThreadSafe
@Slf4j
public class UnSafeDateFormatExample2 {

    //private static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyyMMdd");
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT= DateTimeFormat.forPattern("yyyyMMdd");

    private static int clientTotal=5000;
    private static int threadTotal=200;

    private static int count=0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException | ParseException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    public static void add() throws ParseException {
        DateTime dateTime = SIMPLE_DATE_FORMAT.parseDateTime("20201201");
        log.info("dateTime:{}",dateTime);
    }
}
