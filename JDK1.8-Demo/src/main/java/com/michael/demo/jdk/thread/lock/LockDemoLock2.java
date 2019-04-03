package com.michael.demo.jdk.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JAVA Lock演示DEMO - 使用 ReentrantLock JAVA自带的可重入锁
 *
 * @author Michael
 */
public class LockDemoLock2 {

    int value = 0;

    /**
     * 使用 Lock 相比 synchronized 同步关键字有更灵活的API调用方式，比如 tryLock()方法，不会让其它未获取到锁的线程一直等待。
     */
    Lock lock = new ReentrantLock();

    public void add() {
        this.lock.lock();
        try {
            this.value++;
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemoLock2 demoLock1 = new LockDemoLock2();

        LockDemoLock2Thread t1 = new LockDemoLock2Thread(demoLock1);
        LockDemoLock2Thread t2 = new LockDemoLock2Thread(demoLock1);
        LockDemoLock2Thread t3 = new LockDemoLock2Thread(demoLock1);
        LockDemoLock2ThreadJoin t4 = new LockDemoLock2ThreadJoin(demoLock1, t1, t2, t3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class LockDemoLock2ThreadJoin extends Thread {

    private LockDemoLock2 demoLock1;
    private LockDemoLock2Thread[] threads;

    public LockDemoLock2ThreadJoin(
            LockDemoLock2 demoLock1, LockDemoLock2Thread... threads
    ) {
        this.demoLock1 = demoLock1;
        this.threads = threads;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("child Thread " + threadName + "启动完成");
            for (LockDemoLock2Thread thread : this.threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child Thread " + threadName + "执行完成");
        System.out.println(this.demoLock1.value);
    }
}

class LockDemoLock2Thread extends Thread {

    private LockDemoLock2 demoLock1;

    public LockDemoLock2Thread(LockDemoLock2 demoLock1) {
        this.demoLock1 = demoLock1;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "启动完成");
        for (int j = 0; j < 10000; j++) {
            this.demoLock1.add();
        }
        System.out.println(threadName + "执行完成");
    }
}
