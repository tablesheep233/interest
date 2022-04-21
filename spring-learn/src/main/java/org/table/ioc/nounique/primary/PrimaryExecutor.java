package org.table.ioc.nounique.primary;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.LocalOssService;
import org.table.ioc.nounique.base.MinioOssService;
import org.table.ioc.nounique.base.OssService;

public class PrimaryExecutor implements NoUniqueBeanDefinitionExecutor {
    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(PrimaryExecutor.class);
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.getBean(OssService.class).upload(null);
    }

    @Bean
    public OssService localOssService() {
        return new LocalOssService();
    }


    @Bean
    @Primary
    public OssService minioOssService() {
        return new MinioOssService();
    }
}
