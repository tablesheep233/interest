package org.table.aop.api.autoproxy;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.table.aop.api.advice.HelloServiceInterceptor;
import org.table.aop.api.service.ByeService;
import org.table.aop.api.service.HelloService;

/**
 * {@link BeanNameAutoProxyCreator} demo
 * 通过配置BeanNames来控制需要代理的对象
 */
@Configuration
public class BeanNameAutoProxyMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanNameAutoProxyMain.class);
        applicationContext.scan("org.table.aop.api.service");
        applicationContext.refresh();
        applicationContext.getBean(HelloService.class).hello();
        applicationContext.getBean(ByeService.class).bye();
        applicationContext.close();
    }

    @Bean
    public HelloServiceInterceptor helloServiceInterceptor() {
        return new HelloServiceInterceptor();
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator autoProxyCreator = new BeanNameAutoProxyCreator();
        autoProxyCreator.setBeanNames("helloService*", "byeService*");
//        autoProxyCreator.setBeanNames("byeService*");
        autoProxyCreator.setInterceptorNames("helloServiceInterceptor");
        return autoProxyCreator;
    }
}
