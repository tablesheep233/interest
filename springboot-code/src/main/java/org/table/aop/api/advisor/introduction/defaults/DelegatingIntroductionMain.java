package org.table.aop.api.advisor.introduction.defaults;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.table.aop.api.service.ByeService;
import org.table.aop.api.service.HelloService;
import org.table.aop.api.service.HelloServiceImpl;

/**
 * 通过DelegatingIntroductionInterceptor可以轻松实现接口的引入
 */
public class DelegatingIntroductionMain {

    public static void main(String[] args) {
        HelloService target = new HelloServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        ByeDelegatingIntroductionInterceptor introductionInterceptor = new ByeDelegatingIntroductionInterceptor();
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(introductionInterceptor));
        Object proxy = proxyFactory.getProxy();
        ((HelloService)proxy).hello();
        ((ByeService)proxy).bye();
    }
}
