package com.michael.demo.jdk.thread.tools;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 倒计数器 使用示例
 *
 * @author Michael
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println(Thread.currentThread().getName() + "\t等待2个子线程执行完毕...");
            // 当倒计数器那归零后，执行后续代码
            latch.await();
            System.out.println(Thread.currentThread().getName() + "\t2个子线程已经执行完毕");
            System.out.println(Thread.currentThread().getName() + "\t继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
