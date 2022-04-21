package org.table.aop.proxy.create;

public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("do something...");
    }
}
