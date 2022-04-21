package org.table.config.annotationimport;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {@link ImportBeanDefinitionRegistrar} demo
 */
@Import(value = ImportBeanDefinitionRegistrarMain.RuntimeVersionInfoRegistrar.class)
public class ImportBeanDefinitionRegistrarMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ImportBeanDefinitionRegistrarMain.class);
        applicationContext.refresh();
        RuntimeVersionInfo versionInfo = applicationContext.getBean(RuntimeVersionInfo.class);
        versionInfo.info();
        applicationContext.close();
    }

    public static class RuntimeVersionInfoRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

        public RuntimeVersionInfoRegistrar(BeanFactory beanFactory) {
            System.out.println(this.getClass().getName() + ": constructor");
        }

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(RuntimeVersionInfo.class).getBeanDefinition();
            registry.registerBeanDefinition(importBeanNameGenerator.generateBeanName(beanDefinition, registry), beanDefinition);
        }

        @Override
        public void setEnvironment(Environment environment) {
            System.out.println(this.getClass().getName() + ": setEnvironment");
        }
    }
}
