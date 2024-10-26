package com.learn.example.java21.sec02;

import com.learn.example.java21.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log= LoggerFactory.getLogger(Task.class);
    public static void execute(int i){
        log.info("Starting task:{}",i);
        try {
            method1(i);
        }catch (Exception e){
            log.info("Error on task:{}",i,e);
        }
        log.info("Ending task:{}",i);
    }

    private static void method1(int i){
        CommonUtils.sleep(Duration.ofMillis(300));
        try {
            method2(i);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private static void method2(int i){
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(i);
    }
    private static void method3(int i){
        CommonUtils.sleep(Duration.ofMillis(500));
        if (i==4){
            throw new IllegalArgumentException("I can not be 4.");
        }
    }
}
