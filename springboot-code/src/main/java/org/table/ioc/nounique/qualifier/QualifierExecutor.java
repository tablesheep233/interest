package org.table.ioc.nounique.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.OssService;

@Configuration
public class QualifierExecutor implements NoUniqueBeanDefinitionExecutor {

    @Autowired
    @Qualifier(value = "minio")
    private OssService minioService;

    @Autowired
    @Qualifier(value = "local")
    private OssService localService;

    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.scan("org.table.ioc.nounique.base");
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        QualifierExecutor executor = applicationContext.getBean("qualifierExecutor", QualifierExecutor.class);
        executor.minioService.upload(null);
        executor.localService.upload(null);
    }
}
