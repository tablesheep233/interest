package org.table.lifecycle.scope;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ScopeMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getBeanFactory().registerScope(org.table.lifecycle.scope.ThreadScope.SCOPE, new ThreadScope());
        applicationContext.register(ScopeMain.class);
        applicationContext.refresh();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> applicationContext.getBean(ThreadName.class)).start();
        }

        applicationContext.close();
    }


    @Component
    @org.table.lifecycle.scope.annotation.ThreadScope
    public static class ThreadName implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
