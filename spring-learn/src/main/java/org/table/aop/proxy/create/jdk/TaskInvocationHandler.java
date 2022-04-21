package org.table.aop.proxy.create.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TaskInvocationHandler implements InvocationHandler {

    private Runnable runnable;

    public TaskInvocationHandler(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().isAssignableFrom(Runnable.class)
                && method.getName().equals("run")) {
            System.out.println("invoke before");
            Object result = method.invoke(runnable, args);
            System.out.println("invoke after");
            return result;
        }
        return method.invoke(runnable, args);
    }
}
