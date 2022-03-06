package org.table.ioc.nounique.autowiredbybeanname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.OssService;

@Configuration
public class AutowiredByBeanNameExecutor implements NoUniqueBeanDefinitionExecutor {

    @Autowired
    private OssService localOssService;

    @Autowired
    private OssService minioOssService;


    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.scan("org.table.ioc.nounique.base");
        applicationContext.register(AutowiredByBeanNameExecutor.class);
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        AutowiredByBeanNameExecutor executor = applicationContext.getBean("autowiredByBeanNameExecutor", AutowiredByBeanNameExecutor.class);
        executor.localOssService.upload(null);
        executor.minioOssService.upload(null);
        applicationContext.getBean("localOssService", OssService.class).upload(null);
        applicationContext.getBean("minioOssService", OssService.class).upload(null);
    }
}
