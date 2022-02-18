package org.table.aop.expose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.scheduling.annotation.AsyncAnnotationAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.table.aop.expose.model.Log;
import org.table.aop.expose.service.ILogService;

import java.util.concurrent.ThreadLocalRandom;

/**
 * exposeProxy = true配置测试类
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ExposeProxyMain.class)
class ExposeProxyTest {

    /**
     * 当Bean没有被代理时，使用AopContext会报错
     */
    @Autowired
    @Qualifier(value = "noProxyLogService")
    private ILogService noProxyLogService;

    @Test
    public void testNoProxy() {
        Assertions.assertThrows(IllegalStateException.class, () -> noProxyLogService.collect(generate())).printStackTrace();
    }

    /**
     * 在只是用了 @Async 情况下，使用AopContext会报错，
     * 这是因为 @Async 实现是依靠 {@link AsyncAnnotationBeanPostProcessor}， 而不是 {@link InfrastructureAdvisorAutoProxyCreator}，
     * 所以 exposeProxy = true配置并不会生效
     */
    @Autowired
    private ILogService asyncLogService;

    @Test
    public void testAsyncLog() {
        Assertions.assertThrows(IllegalStateException.class, () -> asyncLogService.collect(generate())).printStackTrace();
    }

    /**
     * 添加@Transactional后，正常运行
     * 这是因为当Bean先被代理时，{@link AsyncAnnotationBeanPostProcessor} 只会在原本的代理对象上面插入{@link AsyncAnnotationAdvisor}
     * （默认情况下{@link InfrastructureAdvisorAutoProxyCreator} 比 {@link AsyncAnnotationBeanPostProcessor} 优先级更高）
     * 对于exposeProxy = true配置已经在代理对象中了
     */
    @Autowired
    private ILogService asyncTransactionalLogService;

    @Test
    public void testAsyncTransactionalLog() {
        Assertions.assertDoesNotThrow(() -> asyncTransactionalLogService.collect(generate()));
    }

    /**
     * @Async 方法处在子线程中，而 {@link AopContext} 是基于 {@link ThreadLocal} 实现的，在子线程中理所当然获取不到当前代理类
     */
    @Autowired
    private ILogService aopContextAsyncLogService;

    @Test
    public void testAopContextAsyncLog() {
        // 异步线程报错
        aopContextAsyncLogService.collect(generate());
    }

    private Log generate() {
        Log log = new Log();
        log.setId(ThreadLocalRandom.current().nextLong());
        log.setInfo("test");
        return log;
    }
}
