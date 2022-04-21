package org.table.aop.proxy.create;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class TaskAdvisor extends DefaultPointcutAdvisor {
    private TaskInterceptor taskInterceptor;
    private Pointcut pointcut;

    public TaskAdvisor(TaskInterceptor taskInterceptor, StaticMethodMatcherPointcut pointcut) {
        super(pointcut, taskInterceptor);
        this.taskInterceptor = taskInterceptor;
        this.pointcut = pointcut;
    }
}
