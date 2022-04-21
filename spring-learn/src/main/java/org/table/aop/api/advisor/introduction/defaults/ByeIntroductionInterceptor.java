package org.table.aop.api.advisor.introduction.defaults;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import org.table.aop.api.service.ByeService;

public class ByeIntroductionInterceptor implements IntroductionInterceptor, ByeService {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 判断当前执行的方法所属类是否实现了目标接口
        // 这里必须要进行相应的判断拦截，否则会在没有被拦截的类方法执行的时候报错我
        // 因为你的其它类所对应的接口并没有在该拦截器中被实现。
        if (implementsInterface(invocation.getMethod().getDeclaringClass())) {
            System.out.println("执行Interceptor实现的ByeService接口方法") ;
            return invocation.getMethod().invoke(this, invocation.getArguments()) ;
        }
        System.out.println("执行原接口方法") ;
        return invocation.proceed() ;
    }

    @Override
    public boolean implementsInterface(Class<?> intf) {
        return intf.isAssignableFrom(this.getClass()) ;
    }

    @Override
    public void bye() {
        System.out.println("bye introduction");
    }
}
