package com.michael.demo.jdk.thread.lock.distributed;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 分布式锁测试用例
 *
 * @author Michael
 */
public class DistributedDemo {

    /** 并发数量 */
    private static int concurrentCount = 20;

    public static void main(String[] args) {

        // 栅栏机制模拟高并发
        CyclicBarrier cyclicBarrier = new CyclicBarrier(concurrentCount);

        // 模块分布式高并发
        for (int i = 0; i < concurrentCount; i++) {
            new Thread(() -> {
                OrderService orderService = new OrderServiceImpl();

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                orderService.getOrderCode();

            }).start();
        }
    }
}
