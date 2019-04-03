package com.michael.demo.jdk.datatype;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * HashMap 多线程不同步DEMO
 *
 * @author Michael
 */
public class ConcurrentHashMapSyncDemo {

    public static void main(String[] args) {
        /** 全局HashMap*/
        Map<Integer, Integer> hashMap = new ConcurrentHashMap<>(256);
        hashMap.put(0, 0);

        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        /** 多线程编辑100次*/
        for (int i = 0; i < count; i++) {
            new Thread(new ConcurrentHashMapEditThread(hashMap, countDownLatch)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** 读取线程*/
        new Thread(new ConcurrentHashMapReadThread(hashMap)).start();

        /** 输出最终结果*/
        System.out.println("Demo1 main value " + hashMap.get(0));
    }
}

class ConcurrentHashMapEditThread implements Runnable {

    Map<Integer, Integer> hashMap;
    CountDownLatch countDownLatch;

    public ConcurrentHashMapEditThread(Map<Integer, Integer> hashMap, CountDownLatch countDownLatch) {
        this.hashMap = hashMap;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.hashMap.put(0, this.hashMap.get(0) + 1);
        this.countDownLatch.countDown();
    }
}

class ConcurrentHashMapReadThread implements Runnable {

    Map<Integer, Integer> hashMap;

    public ConcurrentHashMapReadThread(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        System.out.println("value " + this.hashMap.get(0));
    }
}
