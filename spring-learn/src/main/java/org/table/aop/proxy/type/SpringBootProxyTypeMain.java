package org.table.aop.proxy.type;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.table.aop.proxy.base.InterfaceClass;

@SpringBootApplication(scanBasePackages = "org.table.aop.proxy.base")
public class SpringBootProxyTypeMain {

    /**
     * Spring Boot中默认使用Cglib代理
     * @see org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
     * 在 Spring Boot 默认配置中 spring.aop.proxy-target-class 是 true，
     *     {
     *       "name": "spring.aop.proxy-target-class",
     *       "type": "java.lang.Boolean",
     *       "description": "Whether subclass-based (CGLIB) proxies are to be created (true), as opposed to standard Java interface-based proxies (false).",
     *       "defaultValue": true
     *     }
     */
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(SpringBootProxyTypeMain.class);
        builder.web(WebApplicationType.NONE);
        //在配置文件中设置可切换
//        builder.profiles("aoptype");
        // or
//        builder.properties(Map.of("spring.aop.proxy-target-class", false));
        ConfigurableApplicationContext applicationContext = builder.run(args);
        InterfaceClass proxyObject = (InterfaceClass) applicationContext.getBean("proxyBean");
        // default is CGLIB
        System.out.println(proxyObject.getClass());
        proxyObject.execute();
        applicationContext.close();
    }
}
