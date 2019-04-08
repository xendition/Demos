package com.michael.demo.jdk.io.bio;

import com.michael.demo.jdk.io.AbstractIOConstant;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * BIO示例 —— 客户端
 *
 * @author Michael
 */
public class BioClient extends AbstractIOConstant {

    private static int clientCount = 5000;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        for (int i = 0; i < clientCount; i++) {
            new Thread(new SocketProcess(countDownLatch, i)).start();
        }
        // 等待所有线程处理完成
        countDownLatch.await();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static class SocketProcess implements Runnable {

        CountDownLatch countDownLatch;

        private int i;

        public SocketProcess(CountDownLatch countDownLatch, int i) {
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {
            try (
                    Socket socket = new Socket(host, port);
                    // 客户端发送的信息
                    OutputStream out = socket.getOutputStream();
                    // 接收服务端发送过来的信息
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream(), charset)
                    )
            ) {
                String line = String.format(
                        "来自客户端[%s]的信息 -> hello %s, My name is Michael",
                        Thread.currentThread().getId(),
                        this.i
                );
                out.write(line.getBytes(charset));

                socket.shutdownOutput();

                String serverLine;
                while ((serverLine = bufferedReader.readLine()) != null) {
                    System.out.println("客户端接收到服务端返回信息 -> " + serverLine);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
            }
        }
    }
}
