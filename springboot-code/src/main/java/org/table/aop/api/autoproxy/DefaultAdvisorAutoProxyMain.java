package org.table.aop.api.autoproxy;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.table.aop.api.advice.HelloServiceInterceptor;
import org.table.aop.api.pointcut.SaySameHelloPointcut;
import org.table.aop.api.service.HelloService;

/**
 * {@link DefaultAdvisorAutoProxyCreator} demo
 */
@Configuration
public class DefaultAdvisorAutoProxyMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DefaultAdvisorAutoProxyMain.class);
        applicationContext.scan("org.table.aop.api.service");
        applicationContext.refresh();
        applicationContext.getBean(HelloService.class).saySameHello("2333");
        applicationContext.close();
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        return new DefaultPointcutAdvisor(new SaySameHelloPointcut(), new HelloServiceInterceptor());
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        //自定义bean前缀，与BeanNameAutoProxyCreator类似
//        autoProxyCreator.setUsePrefix(true);
//        autoProxyCreator.setAdvisorBeanNamePrefix("xxx");
        return autoProxyCreator;
    }
}
