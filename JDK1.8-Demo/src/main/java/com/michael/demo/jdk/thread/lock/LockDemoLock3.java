package com.michael.demo.jdk.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JAVA Lock演示DEMO - 使用AtomicInteger(J.U.C包中的原子类)实现,底层使用了CAS机制
 *
 * @author Michael
 */
public class LockDemoLock3 {

    int value = 0;

    /**
     * 使用 Lock 相比 synchronized 同步关键字有更灵活的API调用方式，比如 tryLock()方法，不会让其它未获取到锁的线程一直等待。
     */
    Lock lock = new MyLock();

    public void add() {
        this.lock.lock();
        try {
            this.value++;
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemoLock3 demoLock1 = new LockDemoLock3();

        LockDemoLock3Thread t1 = new LockDemoLock3Thread(demoLock1);
        LockDemoLock3Thread t2 = new LockDemoLock3Thread(demoLock1);
        LockDemoLock3Thread t3 = new LockDemoLock3Thread(demoLock1);
        LockDemoLock3ThreadJoin t4 = new LockDemoLock3ThreadJoin(demoLock1, t1, t2, t3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class LockDemoLock3ThreadJoin extends Thread {

    private LockDemoLock3 demoLock1;
    private LockDemoLock3Thread[] threads;

    public LockDemoLock3ThreadJoin(
            LockDemoLock3 demoLock1, LockDemoLock3Thread... threads
    ) {
        this.demoLock1 = demoLock1;
        this.threads = threads;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("child Thread " + threadName + "启动完成");
            for (LockDemoLock3Thread thread : this.threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child Thread " + threadName + "执行完成");
        System.out.println(this.demoLock1.value);
    }
}

class LockDemoLock3Thread extends Thread {

    private LockDemoLock3 demoLock1;

    public LockDemoLock3Thread(LockDemoLock3 demoLock1) {
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
