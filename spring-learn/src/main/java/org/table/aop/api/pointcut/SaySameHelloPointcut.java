package org.table.aop.api.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.table.aop.api.service.HelloService;

import java.lang.reflect.Method;

public class SaySameHelloPointcut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return HelloService.class.isAssignableFrom(clazz);
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "saySameHello".equals(method.getName())
                        && method.getParameterCount() == 1
                        && method.getReturnType().equals(String.class);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
