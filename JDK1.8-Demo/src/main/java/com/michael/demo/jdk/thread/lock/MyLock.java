package com.michael.demo.jdk.thread.lock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义Lock - 简单实现lock 与 unlock
 *
 * @author Michael
 */
public class MyLock implements Lock {

    /** 当前持有锁的线程 */
    AtomicReference<Thread> owner = new AtomicReference<>();

    /** 用于存储当前正在等待的线程 */
    public LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>();

    @Override
    public void lock() {

        while (!this.owner.compareAndSet(null, Thread.currentThread())) {
            // 未能获取到锁，当前线程需要等待
            this.waiters.add(Thread.currentThread());
            // 当前线程进入等待
            LockSupport.park();
            // 当前线程被唤醒后,从等待集体中删除
            this.waiters.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        if (this.owner.compareAndSet(Thread.currentThread(), null)) {
            // 释放锁之后，等待中的线程需要继续执行
            Object[] objects = this.waiters.toArray();
            for (Object obj : objects) {
                Thread next = (Thread) obj;
                //通知等待中的线程继续执行
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
