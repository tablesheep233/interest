package org.table.aop.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask implements Task {

    @Async
    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName());
    }
}
