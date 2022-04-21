package org.table.aop.proxy.create;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class TaskInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before");
        Object proceed = invocation.proceed();
        System.out.println("after");
        return proceed;
    }
}
