package org.table.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.PropertyDescriptor;

public class LifecycleMain {

    private static int COUNT = 1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LifecycleMain.class);
//        applicationContext.getBeanFactory().addBeanPostProcessor(new CustomBeanPostProcessor()); //若通过直接注册优先级最高,若使用不当会引起一些注解注入失效问题
//        applicationContext.registerBean(BeanDomain.class, BeanDomain::new, (bd) -> bd.setInitMethodName("initMethod"));
        applicationContext.refresh();
        BeanDomain beanDomain = applicationContext.getBean(BeanDomain.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        printlnFormatMessage(beanDomain.stringFiled);
        applicationContext.close();
    }

    @Bean
    public CustomBeanPostProcessor customInstantiationAwareBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }


    public static class CustomBeanPostProcessor implements InstantiationAwareBeanPostProcessor,
            MergedBeanDefinitionPostProcessor, //若不实现它，则属性注入以及@PostConstruct会失效，当然注意实现的方法的返回也可以规避这些问题
            PriorityOrdered {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (isBeanDomain(beanClass)) {
                printlnFormatMessage("InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation");
//                return null; //返回null会终止实例化并进入初始化后阶段
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
        }

//        @Override
        public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
            if (isBeanDomain(beanType)) {
                printlnFormatMessage("MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition");
            }
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (isBeanDomain(bean.getClass())) {
                printlnFormatMessage("InstantiationAwareBeanPostProcessor#postProcessProperties");
                return null; //返回null终止属性注入，若此InstantiationAwareBeanPostProcessor优先级较高，会出现一些注解注入失效问题
            }
            return pvs;
        }

        @Override
        public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
            if (isBeanDomain(bean.getClass())) {
                return null; //返回null终止属性注入，若此InstantiationAwareBeanPostProcessor优先级较高，会出现一些注解注入失效问题
            }
            return pvs;
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (isBeanDomain(bean.getClass())) {
                printlnFormatMessage("BeanPostProcessor#postProcessBeforeInitialization");
                return null; //返回null 后续postProcessBeforeInitialization终止
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (isBeanDomain(bean.getClass())) {
                printlnFormatMessage("BeanPostProcessor#postProcessAfterInitialization");
                return null; //返回null 后续postProcessAfterInitialization终止
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (isBeanDomain(bean.getClass())) {
                printlnFormatMessage("InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation");
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
        }



        @Override
        public int getOrder() {
            return Ordered.LOWEST_PRECEDENCE;
        }
    }

    @Bean
    public String stringFiled() {
        return "stringFiled";
    }


    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
//    @Scope(SCOPE_PROTOTYPE) //不会有完成阶段以及销毁阶段
//    @Lazy //不会有完成阶段
    public BeanDomain beanDomain() {
        return new BeanDomain();
    }

    public static class BeanDomain implements InitializingBean, DisposableBean,
            BeanFactoryAware, ApplicationContextAware, SmartInitializingSingleton {

        //@Resource
        @Autowired
        @Qualifier("stringFiled")
        private String stringFiled = "lueluelue";

        public BeanDomain() {
        }

        @PostConstruct
        public void initPostConstruct() {
            printlnFormatMessage("PostConstruct");
        }

        @PreDestroy
        public void preDestroy() {
            printlnFormatMessage("preDestroy");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            printlnFormatMessage("InitializingBean#afterPropertiesSet");
        }

        @Override
        public void destroy() throws Exception {
            printlnFormatMessage("DisposableBean#destroy");
        }

        public void customInitMethod() {
            printlnFormatMessage("customInitMethod");
        }

        public void customDestroyMethod() {
            printlnFormatMessage("customDestroyMethod");
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            printlnFormatMessage("BeanFactoryAware#setBeanFactory");
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            printlnFormatMessage("ApplicationContextAware#setApplicationContext");
        }

        @Override
        public void afterSingletonsInstantiated() {
            printlnFormatMessage("SmartInitializingSingleton#afterSingletonsInstantiated");
        }
    }

//    @Bean
//    public BeanDomainFactoryBean beanDomainFactoryBean() {
//        return new BeanDomainFactoryBean();
//    }
//
//    //通过FactoryBean创建的Bean只会调用BeanPostProcessor#postProcessAfterInitialization
//    public static class BeanDomainFactoryBean implements FactoryBean<BeanDomain> {
//
//        @Override
//        public BeanDomain getObject() throws Exception {
//            return new BeanDomain();
//        }
//
//        @Override
//        public Class<?> getObjectType() {
//            return BeanDomain.class;
//        }
//    }

    private static boolean isBeanDomain(Class clazz) {
        return BeanDomain.class.equals(clazz);
    }

    private static void printlnFormatMessage(String message) {
        System.out.println(String.format("%s. %s", COUNT++, message));
    }
}