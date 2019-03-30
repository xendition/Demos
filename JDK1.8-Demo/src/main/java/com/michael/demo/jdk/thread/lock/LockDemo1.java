package com.michael.demo.jdk.thread.lock;

/**
 * JAVA Lock演示DEMO - 不能达到效果
 *
 * @author Michael
 */
public class LockDemo1 {

    volatile int i = 0;

    public void add() {
        this.i++;
    }

    public static void main(String[] args) {
        LockDemo1 demo1 = new LockDemo1();

        LockDemo1Thread t1 = new LockDemo1Thread(demo1);
        LockDemo1Thread t2 = new LockDemo1Thread(demo1);
        LockDemo1Thread t3 = new LockDemo1Thread(demo1);
        LockDemo1ThreadJoin t4 = new LockDemo1ThreadJoin(demo1, t1, t2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class LockDemo1ThreadJoin extends Thread {

    private LockDemo1 demo1;
    private LockDemo1Thread[] threads;

    public LockDemo1ThreadJoin(
            LockDemo1 demo1, LockDemo1Thread... threads
    ) {
        this.demo1 = demo1;
        this.threads = threads;
    }

    @Override
    public void run() {
        try {
            for (LockDemo1Thread thread : this.threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.demo1.i);
    }
}

class LockDemo1Thread extends Thread {

    private LockDemo1 demo1;

    public LockDemo1Thread(LockDemo1 demo1) {
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
