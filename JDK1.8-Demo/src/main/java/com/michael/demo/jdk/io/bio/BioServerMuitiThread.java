package com.michael.demo.jdk.io.bio;

import com.michael.demo.jdk.io.AbstractIOConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 服务端程序 - 多线程方式
 *
 * @author Michael
 */
public class BioServerMuitiThread extends AbstractIOConstant {

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("服务端已开启,端口号为 " + port);

            while (true) {

                try {
                    // 阻塞式等待客户端连接,打断点调试可以发现,如果没有客户端连接,主线程会被阻塞,不往下进行
                    Socket socket = serverSocket.accept();

                    new Thread(new SocketProcess(socket)).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SocketProcess implements Runnable {

        Socket socket;

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
