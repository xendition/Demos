package com.michael.demo.jdk.thread.lock.distributed;

import java.util.concurrent.locks.Lock;

/**
 * @author Michael
 */
public class OrderServiceImpl implements OrderService {

    /** 使用 static 标识来模拟共用一个订单编排服务 */
    private static OrderCodeGenerator orderCodeGenerator = new OrderCodeGenerator();

    private Lock lock = new ZookeeperDistributedLock("/abc");

    @Override
    public String getOrderCode() {

        try {
            this.lock.lock();

            String orderCode = orderCodeGenerator.generatorOrderCode();

            System.out.println(Thread.currentThread().getName() + "\t -> \t" + orderCode);

        } finally {
            this.lock.unlock();
        }

        return null;
    }
}
