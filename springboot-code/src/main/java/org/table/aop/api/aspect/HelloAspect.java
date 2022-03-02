package org.table.aop.api.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {

    @Pointcut(value = "execution(* org.table.aop.api.service.HelloService+.*(..))")
    public void pointCut() {
    }

    @Before(value = "org.table.aop.api.aspect.HelloAspect.pointCut()")
    public void before() {
        System.out.println("HelloAspect#before");
    }
}
