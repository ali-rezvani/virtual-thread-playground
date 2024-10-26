package com.learn.example.java21.sec02;

import com.learn.example.java21.util.CommonUtils;

import java.time.Duration;

public class StackTraceDemo {

    public static void main(String[] args) {
        demo(Thread.ofPlatform());
//        demo(Thread.ofVirtual().name("virtual-",1));
        CommonUtils.sleep(Duration.ofSeconds(3));
    }

    private static void demo(Thread.Builder builder){
        for (int i = 1; i <= 20; i++) {
            int finalI = i;
            builder.start(()->Task.execute(finalI));
        }
    }
}
