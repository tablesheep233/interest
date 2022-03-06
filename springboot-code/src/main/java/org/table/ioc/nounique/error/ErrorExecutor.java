package org.table.ioc.nounique.error;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.table.ioc.nounique.NoUniqueBeanDefinitionExecutor;
import org.table.ioc.nounique.base.OssService;

public class ErrorExecutor implements NoUniqueBeanDefinitionExecutor {

    @Override
    public void pre(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.scan("org.table.ioc.nounique.base");
    }

    @Override
    public void doExecute(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.getBean(OssService.class);
    }
}
