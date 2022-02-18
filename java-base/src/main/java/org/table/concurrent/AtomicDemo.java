package org.table.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic demo.
 *
 * @author: tablesheep
 */
public class AtomicDemo {

    private static int val;

    private static AtomicInteger atomicVal = new AtomicInteger(0);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> val++).start();
        }
        for (int i = 0; i < 100; i++) {
            atomicVal.incrementAndGet();
        }
        System.out.println(val);
        System.out.println(atomicVal);
    }
}
