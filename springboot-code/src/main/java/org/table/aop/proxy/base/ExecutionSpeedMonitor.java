package org.table.aop.proxy.base;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Aspect
public class ExecutionSpeedMonitor {

    @Pointcut(value = "execution(* org.table.aop.base.*.*(..))")
    public void pointCut() {
    }

    @SneakyThrows
    @Around(value = "org.table.aop.proxy.base.ExecutionSpeedMonitor.pointCut()")
    public Object executionSpeed(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.nanoTime();
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("method execute expend (nano): " + (System.nanoTime() - startTime));
        return proceed;
    }
}
