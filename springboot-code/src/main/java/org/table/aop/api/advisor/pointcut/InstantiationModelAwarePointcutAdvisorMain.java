package org.table.aop.api.advisor.pointcut;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.table.aop.api.service.HelloService;

@EnableAspectJAutoProxy
@Configuration
public class InstantiationModelAwarePointcutAdvisorMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("org.table.aop.api.service",
                "org.table.aop.api.aspect",
                "org.table.aop.api.advisor.pointcut");
        applicationContext.refresh();
        HelloService helloService = applicationContext.getBean(HelloService.class);
        //断点查看类型，也可以在InstantiationModelAwarePointcutAdvisorImpl构造器断点
        helloService.hello();
        applicationContext.close();
    }
}
