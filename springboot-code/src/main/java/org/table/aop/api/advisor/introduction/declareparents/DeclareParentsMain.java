package org.table.aop.api.advisor.introduction.declareparents;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.table.aop.api.service.ByeService;
import org.table.aop.api.service.HelloService;

@EnableAspectJAutoProxy
@Configuration
public class DeclareParentsMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("org.table.aop.api.service",
                "org.table.aop.api.advisor.introduction.declareparents");
        applicationContext.refresh();
        HelloService helloService = applicationContext.getBean(HelloService.class);
        helloService.hello();
        ((ByeService)helloService).bye();
        applicationContext.close();
    }
}
