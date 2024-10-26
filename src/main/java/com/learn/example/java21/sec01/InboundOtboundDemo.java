package com.learn.example.java21.sec01;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class InboundOtboundDemo {
//    private static final int MAX_PLATFORM=50_000;
    private static final int MAX_PLATFORM=10;
//    private static final int MAX_VIRTUAL=100_000;
    private static final int MAX_VIRTUAL=20;
    public static void main(String[] args) throws InterruptedException {
//        platformThreadDemo();
        virtualThreadDemo1();
//        plarformThreadDemo3_1();
        /*try {
            Thread.sleep(Duration.ofSeconds(12));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    private static void platformThreadDemo(){
        for(int i=0;i<MAX_PLATFORM;i++){
            int j=i;
            Thread thread = new Thread(()->Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void platformThreadDemo2(){
        var builder= Thread.ofPlatform().name("ali",1);
        for(int i=0;i<MAX_PLATFORM;i++){
            int j=i;
            Thread thread = builder.unstarted(()->Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void platformThreadDemo3(){
        var builder= Thread.ofPlatform().daemon().name("daemon",1);
        for(int i=0;i<MAX_PLATFORM;i++){
            int j=i;
//            Thread thread = new Thread(()->Task.ioIntensive(j));
            Thread thread = builder.unstarted(()->Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void platformThreadDemo3_1() throws InterruptedException {
        var latch=new CountDownLatch(MAX_PLATFORM);
        var builder= Thread.ofPlatform().daemon().name("daemon",1);
        for(int i=0;i<MAX_PLATFORM;i++){
            int j=i;
            Thread thread = builder.unstarted(()->{
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }

        latch.await();
    }

    private static void virtualThreadDemo1() throws InterruptedException {
        var latch=new CountDownLatch(MAX_VIRTUAL);
        var builder= Thread.ofVirtual();
        for(int i=0;i<MAX_VIRTUAL;i++){
            int j=i;
            Thread thread=builder.unstarted(()->{
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
