package com.michael.demo.jdk.io.nio;

import com.michael.demo.jdk.io.AbstractIOConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * BIO示例 —— 客户端
 *
 * @author Michael
 */
public class NioClient extends AbstractIOConstant {

    private static int clientCount = 3000;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        CyclicBarrier barrier = new CyclicBarrier(clientCount);

        for (int i = 0; i < clientCount; i++) {
            new Thread(new SocketProcess(barrier, countDownLatch, i)).start();
        }
        // 等待所有线程处理完成
        countDownLatch.await();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static class SocketProcess implements Runnable {

        private CyclicBarrier barrier;

        private CountDownLatch countDownLatch;

        private int i;

        public SocketProcess(CyclicBarrier barrier, CountDownLatch countDownLatch, int i) {
            this.barrier = barrier;
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {

            try {
                this.barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            try (
                    SocketChannel sc = SocketChannel.open()
            ) {

                // 连接会阻塞
                if (sc.connect(new InetSocketAddress(port))) {

                    String line = String.format(
                            "来自客户端[%s]的信息 -> hello %s, My name is [%s]",
                            Thread.currentThread().getId(),
                            this.i,
                            this.i
                    );
                    sc.write(ByteBuffer.wrap(line.getBytes(charset)));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
            }
        }
    }
}
