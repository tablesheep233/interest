package org.table.ioc.nounique.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.OssService;

@Configuration
public class ErrorExecutor implements NoUniqueBeanDefinitionExecutor {
//
//    @Autowired
//    private OssService ossService;

    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(ErrorExecutor.class);
        applicationContext.scan("org.table.ioc.nounique.base");
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.getBean(OssService.class);
    }

//    @Bean
//    public String minio(OssService ossService) {
//        return ossService.getClass().getName();
//    }
}
