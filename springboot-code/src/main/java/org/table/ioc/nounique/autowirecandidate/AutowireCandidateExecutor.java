package org.table.ioc.nounique.autowirecandidate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.LocalOssService;
import org.table.ioc.nounique.base.MinioOssService;
import org.table.ioc.nounique.base.OssService;

public class AutowireCandidateExecutor implements NoUniqueBeanDefinitionExecutor {

    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(AutowireCandidateExecutor.class);
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("autowire bean: " + applicationContext.getBean(String.class));
        applicationContext.getBean(OssService.class).upload(null);
        applicationContext.getBean("minioOssService", OssService.class).upload(null);
    }

    @Bean
    public OssService localOssService() {
        return new LocalOssService();
    }

    /**
     * 配置 autowireCandidate = false 后，将不会自动注入，getBeanByType时也不会，只有根据名称获取时才能获取到
     * @return
     */
    @Bean(autowireCandidate = false)
    public OssService minioOssService() {
        return new MinioOssService();
    }

    @Bean
    public String autowireOssServiceName(OssService ossService) {
        return ossService.getClass().getName();
    }
}
