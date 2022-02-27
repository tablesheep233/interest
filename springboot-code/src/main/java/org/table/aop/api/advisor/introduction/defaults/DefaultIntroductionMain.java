package org.table.aop.api.advisor.introduction.defaults;

import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.table.aop.api.service.ByeService;
import org.table.aop.api.service.HelloService;
import org.table.aop.api.service.HelloServiceImpl;

/**
 * 使用IntroductionInterceptor和DefaultIntroductionAdvisor实现接口的引入
 */
public class DefaultIntroductionMain {

    public static void main(String[] args) {
        HelloService target = new HelloServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        ByeIntroductionInterceptor introductionInterceptor = new ByeIntroductionInterceptor();
        //不指定Introduction，会发生导致不能代理引入的接口
//        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(introductionInterceptor));
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(introductionInterceptor,
                new IntroductionInfo() {
                    @Override
                    public Class<?>[] getInterfaces() {
                        return new Class[]{ByeService.class};
                    }
                }));
        Object proxy = proxyFactory.getProxy();
        ((HelloService)proxy).hello();
        ((ByeService)proxy).bye();
    }
}
