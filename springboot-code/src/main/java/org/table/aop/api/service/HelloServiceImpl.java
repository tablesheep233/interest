package org.table.aop.api.service;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService{

    public static final String HELLO = "hello";

    @Override
    public void hello() {
        System.out.println(HELLO);
    }

    @Override
    public String saySameHello(String helloMsg) {
        return helloMsg;
    }


}
