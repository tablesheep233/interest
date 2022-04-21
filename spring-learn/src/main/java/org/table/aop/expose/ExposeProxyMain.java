package org.table.aop.expose;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * ExposeProxyMain.
 *
 * @author: tablesheep
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
@SpringBootApplication
public class ExposeProxyMain {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(ExposeProxyMain.class).run(args);
    }
}

