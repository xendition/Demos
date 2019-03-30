package com.michael.demo.jdk.thread.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * JAVA Lock演示DEMO - 使用Unsafe类直接调用底层CAS的方式处理
 *
 * @author Michael
 */
public class LockDemoCas {

    volatile long value = 0;

    static Unsafe unsafe;
    static long valueOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);

            unsafe = (Unsafe) field.get(null);

            valueOffset = unsafe.objectFieldOffset(LockDemoCas.class.getDeclaredField("value"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {

        // 这行代码的效果与下面的效果一样
        // unsafe.getAndAddInt(this, valueOffset, 1);

        int current;
        do {
            current = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapInt(this, valueOffset, current, current + 1));
    }


    public static void main(String[] args) {
        LockDemoCas demoCas = new LockDemoCas();

        LockDemoCasThread t = new LockDemoCasThread(demoCas);
        LockDemoCasThread t1 = new LockDemoCasThread(demoCas);
        LockDemoCasThread t2 = new LockDemoCasThread(demoCas);
        LockDemoCasThread t3 = new LockDemoCasThread(demoCas);

        LockDemoCasThreadJoin t4 = new LockDemoCasThreadJoin(demoCas, t, t1, t2, t3);

        t.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class LockDemoCasThreadJoin extends Thread {

    private LockDemoCas demoCas;
    private LockDemoCasThread[] threads;

    public LockDemoCasThreadJoin(
            LockDemoCas demoCas, LockDemoCasThread... threads
    ) {
        this.demoCas = demoCas;
        this.threads = threads;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("child Thread " + threadName + "启动完成");
        try {
            for (LockDemoCasThread thread : this.threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child Thread " + threadName + "执行完成");
        System.out.println(this.demoCas.value);
    }
}

class LockDemoCasThread extends Thread {

    private LockDemoCas demoCas;

    public LockDemoCasThread(LockDemoCas demoCas) {
        this.demoCas = demoCas;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "启动完成");

        for (int j = 0; j < 10000; j++) {
            this.demoCas.add();
        }
        System.out.println(threadName + "执行完成");
    }
}
