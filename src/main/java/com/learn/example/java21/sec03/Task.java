package com.learn.example.java21.sec03;

import com.learn.example.java21.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static final Logger log= LoggerFactory.getLogger(com.learn.example.java21.sec02.Task.class);

    public static void cpuIntensive(int i) {
//        log.info("starting CPU task.Thread infos:{}", Thread.currentThread());
        var timeTaken= CommonUtils.timer(()->findFib(i));
//        log.info("CPU task finished.Time taken:{} ms", timeTaken);

    }
    public static long findFib(long input) {
        if (input < 2) {
            return input;
        }
        return findFib(input-1) + findFib(input-2);
    }
}
