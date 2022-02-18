package org.table.aop.async;

import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableAsync
public class AsyncMain {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        return new TaskSchedulerBuilder().build();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AsyncMain.class);
        applicationContext.scan("org.table.aop.async");
        applicationContext.refresh();
        Task asyncTask = (Task) applicationContext.getBean("asyncTask");
        System.out.println(Thread.currentThread().getName());
        asyncTask.execute();
        applicationContext.close();
    }
}
