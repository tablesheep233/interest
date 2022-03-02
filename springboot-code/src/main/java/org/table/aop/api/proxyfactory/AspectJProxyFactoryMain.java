package org.table.aop.api.proxyfactory;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.table.aop.api.aspect.HelloAspect;
import org.table.aop.api.service.HelloService;
import org.table.aop.api.service.HelloServiceImpl;

/**
 * {@link AspectJProxyFactory} demo
 */
public class AspectJProxyFactoryMain {

    public static void main(String[] args) {
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(new HelloServiceImpl());
        proxyFactory.addAspect(new HelloAspect());
        Object proxy = proxyFactory.getProxy();
        ((HelloService)proxy).hello();
    }
}
