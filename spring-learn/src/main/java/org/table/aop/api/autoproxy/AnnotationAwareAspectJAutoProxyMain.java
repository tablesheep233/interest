package org.table.aop.api.autoproxy;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.table.aop.api.advisor.pointcut.InstantiationModelAwarePointcutAdvisorMain;
import org.aspectj.lang.annotation.Aspect;

/**
 * {@link Aspect} 注解自动代理demo
 *
 * {@link AnnotationAwareAspectJAutoProxyCreator} demo
 * @see InstantiationModelAwarePointcutAdvisorMain
 */
public class AnnotationAwareAspectJAutoProxyMain {

    public static void main(String[] args) {
        InstantiationModelAwarePointcutAdvisorMain.main(args);
    }
}
