package org.table.ioc.nounique.priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.OssService;


@Configuration
public class PriorityExecutor implements NoUniqueBeanDefinitionExecutor {
    @Autowired
    private OssService ossService;

    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.scan("org.table.ioc.nounique.priority");
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        PriorityExecutor executor = applicationContext.getBean("priorityExecutor", PriorityExecutor.class);
        executor.ossService.upload(null);
        System.out.println(applicationContext.getBean("minio"));
    }

    @Bean
    public String minio(OssService ossService) {
        return ossService.getClass().getName();
    }
}
