package com.michael.demo.jdk.io.bio;

import com.michael.demo.jdk.io.AbstractIOConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BIO 服务端程序 - 使用线程池(BIO效率最高的方式)
 *
 * @author Michael
 */
public class BioServerThreadPool extends AbstractIOConstant {

    public static void main(String[] args) {

        int threadPoolSize = 1000;

        //构造一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(threadPoolSize, threadPoolSize,
                                                               0L, TimeUnit.MILLISECONDS,
                                                               new LinkedBlockingQueue<>(),
                                                               new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("服务端已开启,端口号为 " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new SocketProcess(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }

    static class SocketProcess implements Runnable {

        private Socket socket;

        public SocketProcess(Socket socket) {
            super();
            this.socket = socket;
        }

        @Override
        public void run() {

            try (
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(this.socket.getInputStream(), charset)
                    );

                    // 服务端发送的信息
                    OutputStream out = this.socket.getOutputStream()
            ) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("[" + Thread.currentThread().getId() + "]["
                                       + System.currentTimeMillis() + "]服务端接收到客户端信息 -> " + line);
                }

                this.socket.shutdownInput();

                out.write(("[" + (Thread.currentThread().getId() + "]服务端收到了你的数据")).getBytes(charset));

                // 模拟业务处理
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
