package org.table.aop.api.service;

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
