package org.table.aop.proxy.create.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.table.aop.proxy.create.Task;

import java.lang.reflect.Method;

public class CglibMain {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Task.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib intercept before");
                Object result = methodProxy.invokeSuper(o, args);
                System.out.println("cglib intercept after");
                return result;
            }
        });
        Task proxy = (Task) enhancer.create();
        proxy.run();
    }
}
