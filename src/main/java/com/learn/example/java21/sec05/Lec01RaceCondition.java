package com.learn.example.java21.sec05;

import com.learn.example.java21.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec01RaceCondition {
    private static final Logger log = LoggerFactory.getLogger(Lec01RaceCondition.class);
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
//        demo(Thread.ofPlatform());
        demo(Thread.ofVirtual());
        CommonUtils.sleep(Duration.ofSeconds(2));

        log.info("List size: {}", list.size());
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                        log.info("thread-{} started", Thread.currentThread().getName());
                        for (int j = 0; j < 200; j++) {
                           add();
                        }
                        log.info("thread-{} started", Thread.currentThread().getName());
                    }
            );
        }
    }

    private static void add() {
        list.add(1);
    }
}
