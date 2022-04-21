package org.table.aop.api.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class HelloServiceMethodPointcut extends StaticMethodMatcherPointcut {

    private final Class<?> targetClass;

    private final String methodName;

    public HelloServiceMethodPointcut(Class<?> targetClass, String methodName) {
        this.targetClass = targetClass;
        this.methodName = methodName;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return methodName.equals(method.getName())
                && this.targetClass.isAssignableFrom(targetClass);
    }
}
