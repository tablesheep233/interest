package org.table.config.annotationimport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * {@link Import} class demo
 */
@Import(value = RuntimeVersionInfo.class)
public class ImportClassMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ImportClassMain.class);
        applicationContext.refresh();
        RuntimeVersionInfo versionInfo = applicationContext.getBean(RuntimeVersionInfo.class);
        versionInfo.info();
        applicationContext.close();
    }
}
