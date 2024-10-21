package com.learn.example.java21.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    public static final Logger log= LoggerFactory.getLogger(Task.class);
    public static void ioIntensive(int i){
        try {
            log.info("Starting I/O:{}",i);
            Thread.sleep(Duration.ofSeconds(10));
            log.info("Ending I/O:{}",i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
