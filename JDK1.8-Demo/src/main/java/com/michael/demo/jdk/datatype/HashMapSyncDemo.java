package com.michael.demo.jdk.datatype;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * HashMap 多线程不同步DEMO
 *
 * @author Michael
 */
public class HashMapSyncDemo {

    public static void main(String[] args) {
        /** 全局HashMap*/
        Map<Integer, Integer> hashMap = new HashMap(256);
        hashMap.put(0, 0);

        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        /** 多线程编辑100次*/
        for (int i = 0; i < count; i++) {
            new Thread(new HashMapEditThread(hashMap, countDownLatch)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** 读取线程*/
        new Thread(new HashMapReadThread(hashMap)).start();

        /** 输出最终结果*/
        System.out.println("Demo1 main value " + hashMap.get(0));
    }
}

class HashMapEditThread implements Runnable {

    Map<Integer, Integer> hashMap;
    CountDownLatch countDownLatch;

    public HashMapEditThread(Map<Integer, Integer> hashMap, CountDownLatch countDownLatch) {
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

class HashMapReadThread implements Runnable {

    Map<Integer, Integer> hashMap;

    public HashMapReadThread(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        System.out.println("value " + this.hashMap.get(0));
    }
}
