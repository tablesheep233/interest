package org.table.aop.proxy.create;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class ApiCreateMain {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(new Task());
        proxyFactory.setInterfaces(Runnable.class);
        proxyFactory.addAdvisor(new TaskAdvisor(new TaskInterceptor(), new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "run".equals(method.getName())
                        && Runnable.class.isAssignableFrom(targetClass);
            }
        }));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new TaskInterceptor()));

        Task proxy = (Task)proxyFactory.getProxy();
        proxy.run();
    }
}
