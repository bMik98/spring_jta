package com.becomejavasenior.controller;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * Created by shevchenko on 17.05.2016.
 */
@Component
public class ScanTime {
    private long startTime = 0L;

    public void getStartTime(JoinPoint joinPoint){
        System.out.print("           Method " + joinPoint.getSignature().getName() + " of " +
                joinPoint.getTarget().getClass().getSimpleName());
        startTime = System.nanoTime();
    }

    public void getSpentTime(){
        System.out.println("              spent time = " + (System.nanoTime() - startTime)/1000000.0 + " ms");
    }
}
