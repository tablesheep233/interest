package org.table.aop.proxy.base;

import org.springframework.stereotype.Component;

@Component("proxyBean")
public class ImplementObject implements InterfaceClass{
    @Override
    public void execute() {
        System.out.println("do something...");
    }
}
