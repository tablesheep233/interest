package org.table.aop.proxy.type;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.table.aop.proxy.base.InterfaceClass;

/**
 * Spring framework proxy type main.
 *
 * @author: tablesheep
 */
@EnableAspectJAutoProxy
public class SpringFrameworkProxyTypeMain {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringFrameworkProxyTypeMain.class);
        applicationContext.scan("org.table.aop.proxy.base");
        applicationContext.refresh();
        InterfaceClass proxyObject = (InterfaceClass) applicationContext.getBean("proxyBean");
        // com.sun.proxy
        System.out.println(proxyObject.getClass());
        proxyObject.execute();
        applicationContext.close();
    }
}
