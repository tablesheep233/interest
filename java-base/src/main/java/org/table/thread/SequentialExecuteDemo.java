package org.table.thread;

public class SequentialExecuteDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("thread1"));
        Thread thread2 = new Thread(() -> System.out.println("thread2"));
        Thread thread3 = new Thread(() -> System.out.println("thread3"));
        spin(thread1, thread2, thread3);
    }

    private static void sleep(Thread thread1, Thread thread2, Thread thread3) throws InterruptedException {
        sleep(thread1);
        sleep(thread2);
        sleep(thread3);
    }

    private static void sleep(Thread thread) throws InterruptedException {
        thread.start();
        while (thread.isAlive()) {
            Thread.sleep(10);
        }
    }

    private static void spin(Thread thread1, Thread thread2, Thread thread3) throws InterruptedException {
        spin(thread1);
        spin(thread2);
        spin(thread3);
    }

    private static void spin(Thread thread) throws InterruptedException {
        thread.start();
        while (thread.isAlive()) {
        }
    }

    private static void join(Thread thread1, Thread thread2, Thread thread3) throws InterruptedException {
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }

    private static void wait(Thread thread1, Thread thread2, Thread thread3) throws InterruptedException {
        wait(thread1);
        wait(thread2);
        wait(thread3);
    }

    private static void wait(Thread thread) throws InterruptedException {
        thread.start();
        synchronized (thread) {
            while (thread.isAlive()) {
                //线程结束运行时会调用notifyAll,在JDK源码中
                thread.wait();
            }
        }
    }
}
