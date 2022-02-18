package org.table.aop.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.table.aop.async.AsyncMain;

@EnableCaching
@Slf4j
public class CacheMain {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CacheMain.class);
        applicationContext.scan("org.table.aop.cache");
        applicationContext.refresh();
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        log.info(userService.load(1L).toString());
        log.info(userService.load(1L).toString());
        log.info(userService.load(1L).toString());
        applicationContext.close();
    }
}
