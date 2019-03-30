package com.michael.demo.jdk.thread.lock;

/**
 * JAVA Lock演示DEMO - 使用AtomicInteger(J.U.C包中的原子类)实现,底层使用了CAS机制
 *
 * @author Michael
 */
public class LockDemoLock1 {

    int value = 0;

    public void add() {
        synchronized (this) {
            this.value++;
        }
    }

    public static void main(String[] args) {
        LockDemoLock1 demoLock1 = new LockDemoLock1();

        LockDemoLock1Thread t1 = new LockDemoLock1Thread(demoLock1);
        LockDemoLock1Thread t2 = new LockDemoLock1Thread(demoLock1);
        LockDemoLock1Thread t3 = new LockDemoLock1Thread(demoLock1);
        LockDemoLock1ThreadJoin t4 = new LockDemoLock1ThreadJoin(demoLock1, t1, t2, t3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class LockDemoLock1ThreadJoin extends Thread {

    private LockDemoLock1 demoLock1;
    private LockDemoLock1Thread[] threads;

    public LockDemoLock1ThreadJoin(
            LockDemoLock1 demoLock1, LockDemoLock1Thread... threads
    ) {
        this.demoLock1 = demoLock1;
        this.threads = threads;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("child Thread " + threadName + "启动完成");
            for (LockDemoLock1Thread thread : this.threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child Thread " + threadName + "执行完成");
        System.out.println(this.demoLock1.value);
    }
}

class LockDemoLock1Thread extends Thread {

    private LockDemoLock1 demoLock1;

    public LockDemoLock1Thread(LockDemoLock1 demoLock1) {
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
