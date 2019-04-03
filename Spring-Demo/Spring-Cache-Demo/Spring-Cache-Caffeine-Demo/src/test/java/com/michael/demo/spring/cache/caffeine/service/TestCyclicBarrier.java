package com.michael.demo.spring.cache.caffeine.service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public TestCyclicBarrier() {
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程均到达栅栏位置，开始下一轮");
            }
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            this.workers[i] = new Worker(i);
        }
    }

    private class Worker implements Runnable {
        int i;

        public Worker(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int index = 1; index < 2; index++) {
                System.out.println("线程" + this.i + "第" + index + "次到达栅栏位置，等待其他线程!");
                try {
                    TestCyclicBarrier.this.barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(1);
            }
        }

    }

    public void start() {
        for (int i = 0; i < this.workers.length; i++) {
            new Thread(this.workers[i]).start();
        }
    }

    public static void main(String[] args) {
        new TestCyclicBarrier().start();
    }
}
