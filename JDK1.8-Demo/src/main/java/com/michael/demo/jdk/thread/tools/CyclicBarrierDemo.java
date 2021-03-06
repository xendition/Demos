package com.michael.demo.jdk.thread.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏Demo  可以实现让一组线程等待至某个状态之后再全部同时执行
 *
 * @author Michael
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int count = 4;
        CyclicBarrier barrier = new CyclicBarrier(
                count,
                new Thread(
                        () -> System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...")
                )
        );
        for (int i = 0; i < count; i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 栅栏的可重用Demo如下
        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < count; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(1000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                this.cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
