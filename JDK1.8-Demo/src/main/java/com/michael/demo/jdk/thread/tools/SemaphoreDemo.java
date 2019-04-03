package com.michael.demo.jdk.thread.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 信号量Demo
 *
 * @author Michael
 */
public class SemaphoreDemo {

    /**
     * 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用
     */
    public static void main(String[] args) {


        //工人数
        int workerCount = 20;

        //机器数目
        Semaphore machine = new Semaphore(3);

        final CountDownLatch count = new CountDownLatch(workerCount);

        for (int i = 0; i < workerCount; i++) {
            new Worker(i, machine, count).start();
        }

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n$$$$$$$$$    所有工作处理完成    $$$$$$$$$");

    }

    static class Worker extends Thread {
        private int num;
        private Semaphore machine;
        private CountDownLatch count;

        public Worker(int num, Semaphore machine, CountDownLatch count) {
            this.num = num;
            this.machine = machine;
            this.count = count;
        }

        @Override
        public void run() {
            try {

                // 获取许可(类似锁)
                this.machine.acquire();
                System.out.println("工人 " + this.num + " 占用一个机器在生产...");
                Thread.sleep(1000);
                System.out.println("工人 " + this.num + " 释放出机器");

                this.count.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放许可
                this.machine.release();
            }
        }
    }

}
