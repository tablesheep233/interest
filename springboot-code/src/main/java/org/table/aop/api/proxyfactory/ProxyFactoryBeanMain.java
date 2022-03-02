package org.table.aop.api.proxyfactory;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.table.aop.api.advice.HelloServiceInterceptor;
import org.table.aop.api.service.HelloService;
import org.table.aop.api.service.HelloServiceImpl;

/**
 * {@link ProxyFactoryBean} demo
 */
@EnableAspectJAutoProxy
@Configuration
public class ProxyFactoryBeanMain {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ProxyFactoryBeanMain.class);
        applicationContext.refresh();
        HelloService helloService = applicationContext.getBean("helloServiceProxyFactoryBean", HelloService.class);
        helloService.hello();
        applicationContext.close();
    }

    @Bean
    public HelloService helloService() {
        return new HelloServiceImpl();
    }

    @Bean
    public HelloServiceInterceptor helloServiceInterceptor() {
        return new HelloServiceInterceptor();
    }

    @Bean
    public ProxyFactoryBean helloServiceProxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetName("helloService");
        proxyFactoryBean.setInterceptorNames("helloServiceInterceptor");
//        proxyFactoryBean.setTargetSource(new HotSwappableTargetSource(new HelloServiceImpl()));
        return proxyFactoryBean;
    }

}
