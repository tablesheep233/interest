package org.table.aop.api.proxyfactory;

import org.springframework.aop.framework.ProxyFactory;
import org.table.aop.api.advice.HelloServiceInterceptor;
import org.table.aop.api.service.HelloService;
import org.table.aop.api.service.HelloServiceImpl;

/**
 *  * {@link ProxyFactory} demo
 */
public class ProxyFactoryMain {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(HelloService.class, new HelloServiceInterceptor());
        proxyFactory.setTarget(new HelloServiceImpl());
        Object proxy = proxyFactory.getProxy();
        ((HelloService)proxy).hello();
    }
}
