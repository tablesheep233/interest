package org.table.aop.api.pointcut;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.Pointcuts;
import org.table.aop.api.advice.HelloServiceInterceptor;
import org.table.aop.api.service.HelloService;
import org.table.aop.api.service.HelloServiceImpl;

import static org.table.aop.api.service.HelloServiceImpl.HELLO;

public class PointcutMain {

    public static void main(String[] args) {
        SaySameHelloPointcut saySameHelloPointcut = new SaySameHelloPointcut();
        HelloServiceMethodPointcut helloServiceMethodPointcut = new HelloServiceMethodPointcut(HelloService.class, "hello");

        Pointcut pointcut = Pointcuts.union(saySameHelloPointcut, helloServiceMethodPointcut);
//        Pointcut pointcut = new ComposablePointcut(saySameHelloPointcut.getClassFilter(), helloServiceMethodPointcut);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new HelloServiceInterceptor());

        ProxyFactory proxyFactory = new ProxyFactory(new HelloServiceImpl());
        proxyFactory.addAdvisor(advisor);

        HelloService helloService = (HelloService) proxyFactory.getProxy();
        helloService.hello();
        System.out.println(helloService.saySameHello(HELLO));
    }
}
