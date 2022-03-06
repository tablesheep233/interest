package org.table.ioc.nounique;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface NoUniqueBeanDefinitionExecutor {

    default void execute() {
        startTag();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        pre(applicationContext);
        applicationContext.refresh();
        doExecute(applicationContext);
        applicationContext.close();
        endTag();
    }

    default void startTag() {
        System.out.println("------- " + this.getClass().getSimpleName() + ": start -------");
    }

    void pre(AnnotationConfigApplicationContext applicationContext);

    void doExecute(AnnotationConfigApplicationContext applicationContext);

    default void endTag() {
        System.out.println("------- " + this.getClass().getSimpleName() + ": end -------");
    }
}
