package com.learn.example.java21.sec04;

import com.learn.example.java21.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeShedulingDemo {
    public static final Logger log = LoggerFactory.getLogger(CooperativeShedulingDemo.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    public static void main(String[] args) {
        var builder = Thread.ofVirtual();
        var t1 = builder.unstarted(() -> demo(1));
        var t2 = builder.unstarted(() -> demo(2));
        var t3 = builder.unstarted(() -> demo(3));
        t1.start();
        t2.start();
        t3.start();

        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    public static void demo(int threadNumber) {
        log.info("Started thread-{} ", threadNumber);
        for (int i = 0; i < 10; i++) {
            log.info("Thread {} is running. Thread:{}", i, Thread.currentThread());
            if ((threadNumber == 1 && i % 2 == 0) || threadNumber == 2) {
                Thread.yield();
            }
        }

        log.info("Finished thread-{} thread", threadNumber);
    }
}
