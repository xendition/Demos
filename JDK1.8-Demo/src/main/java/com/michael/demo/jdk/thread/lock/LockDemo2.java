package com.michael.demo.jdk.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JAVA Lock演示DEMO - 使用AtomicInteger(J.U.C包中的原子类)实现,底层使用了CAS机制
 *
 * @author Michael
 */
public class LockDemo2 {

    AtomicInteger i = new AtomicInteger(0);

    public void add() {
        this.i.getAndIncrement();
    }

    public static void main(String[] args) {
        LockDemo2 demo2 = new LockDemo2();

        LockDemo2Thread t1 = new LockDemo2Thread(demo2);
        LockDemo2Thread t2 = new LockDemo2Thread(demo2);
        LockDemo2Thread t3 = new LockDemo2Thread(demo2);
        LockDemo2ThreadJoin t4 = new LockDemo2ThreadJoin(demo2, t1, t2, t3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class LockDemo2ThreadJoin extends Thread {

    private LockDemo2 demo1;
    private LockDemo2Thread[] threads;

    public LockDemo2ThreadJoin(
            LockDemo2 demo1, LockDemo2Thread... threads
    ) {
        this.demo1 = demo1;
        this.threads = threads;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("child Thread " + threadName + "启动完成");
            for (LockDemo2Thread thread : this.threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child Thread " + threadName + "执行完成");
        System.out.println(this.demo1.i);
    }
}

class LockDemo2Thread extends Thread {

    private LockDemo2 demo1;

    public LockDemo2Thread(LockDemo2 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "启动完成");
        for (int j = 0; j < 10000; j++) {
            this.demo1.add();
        }
        System.out.println(threadName + "执行完成");
    }
}
