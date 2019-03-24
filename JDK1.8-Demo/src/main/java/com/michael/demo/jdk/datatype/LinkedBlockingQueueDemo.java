package com.michael.demo.jdk.datatype;

import java.util.concurrent.*;

/**
 * @author Michael
 */
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //1、创建一个BlockingQueue
        int MAX_NUM = 10;
        LinkedBlockingQueue<LinkedBlockingQueueApple> queue = new LinkedBlockingQueue<>(MAX_NUM);

        //2、创建一个生产者，一个消费者
        LinkedBlockingQueueProducer linkedBlockingQueueProducer = new LinkedBlockingQueueProducer(queue, MAX_NUM);
        LinkedBlockingQueueConsumer linkedBlockingQueueConsumer = new LinkedBlockingQueueConsumer(queue, MAX_NUM);

        //3、开启两个线程

        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = new ThreadPoolExecutor(
//                2, 10, 0L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(),
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        return null;
//                    }
//                }
//        );

        executorService.submit(linkedBlockingQueueProducer);
        executorService.submit(linkedBlockingQueueConsumer);

        // 程序运行10s后，所有任务停止
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        executorService.shutdownNow();
    }
}

class LinkedBlockingQueueApple {

    int id;

    public LinkedBlockingQueueApple(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LinkedBlockingQueueApple: " + id;
    }
}

class LinkedBlockingQueueProducer extends Thread {
    //1、通过构造函数传入阻塞队列
    public LinkedBlockingQueue<LinkedBlockingQueueApple> queue;
    private int MAX_NUM = 0;

    public LinkedBlockingQueueProducer(LinkedBlockingQueue<LinkedBlockingQueueApple> queue, int MAX_NUM) {
        this.queue = queue;
        this.MAX_NUM = MAX_NUM;
    }

    @Override
    public void run() {

        //只要阻塞队列没有满，就可以一直往里面放苹果
        while (true) {
            try {
                queue.put(new LinkedBlockingQueueApple(queue.size()));
                System.out.println("LinkedBlockingQueueProducer queue size " + queue.size());
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println("LinkedBlockingQueueApple is enough !!  " + queue.size());
                e.printStackTrace();
            }
        }
    }
}

class LinkedBlockingQueueConsumer extends Thread {
    public LinkedBlockingQueue<LinkedBlockingQueueApple> queue;
    private int MAX_NUM = 0;

    public LinkedBlockingQueueConsumer(LinkedBlockingQueue<LinkedBlockingQueueApple> queue, int MAX_NUM) {
        this.queue = queue;
        this.MAX_NUM = MAX_NUM;
    }

    @Override
    public void run() {
        //只要阻塞队列不为空，就一直从里面取苹果
        while (true) {
            try {
                queue.take();
                System.out.println("LinkedBlockingQueueConsumer LinkedBlockingQueueApple is left : " + queue.size());
                Thread.sleep(60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println("No LinkedBlockingQueueApple " + queue.size());
                e.printStackTrace();
            }
        }
    }

}
