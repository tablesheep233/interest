package org.table.aop.proxy.create.jdk;

import org.table.aop.proxy.create.Task;

import java.lang.reflect.Proxy;

public class JdkProxyMain {
    public static void main(String[] args) {
        Runnable proxy = (Runnable) Proxy.newProxyInstance(JdkProxyMain.class.getClassLoader(),
                new Class[]{Runnable.class}, new TaskInvocationHandler(new Task()));
        proxy.run();
    }
}
