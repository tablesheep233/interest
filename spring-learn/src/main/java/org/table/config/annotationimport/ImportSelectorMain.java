package org.table.config.annotationimport;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {@link ImportSelector} demo
 */
@Import(ImportSelectorMain.RuntimeVersionInfoSelector.class)
public class ImportSelectorMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ImportSelectorMain.class);
        applicationContext.refresh();
        RuntimeVersionInfo versionInfo = applicationContext.getBean(RuntimeVersionInfo.class);
        versionInfo.info();
        applicationContext.close();
    }

    public static class RuntimeVersionInfoSelector implements ImportSelector, BeanFactoryAware {

        public RuntimeVersionInfoSelector(Environment environment) {
            System.out.println(this.getClass().getName() + ": constructor");
        }

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{RuntimeVersionInfo.class.getName()};
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            System.out.println(this.getClass().getName() + ": setBeanFactory");
        }
    }
}
