package com.michael.demo.jdk.io.bio;

import com.michael.demo.jdk.io.AbstractIOConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 服务端程序 - 单线程,只有一个线程来处理客户端的业务
 *
 * @author Michael
 */
public class BioServerSingleThread extends AbstractIOConstant {

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("服务端已开启,端口号为 " + port);

            while (true) {

                try (
                        // 阻塞式等待客户端连接,打断点调试可以发现,如果没有客户端连接,主线程会被阻塞,不往下进行
                        Socket socket = serverSocket.accept();

                        // 服务端发送的信息
                        OutputStream out = socket.getOutputStream();

                        // 接收客户端发送过来的信息
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), charset)
                        )
                ) {
                    // System.out.println("有客户端来连接服务器了,服务器地址为 " + socket.getInetAddress().getHostAddress());

                    String line;
                    // 客户端数据没准备好会阻塞
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println("[" + System.currentTimeMillis() + "]服务端接收到客户端信息 -> " + line);
                    }

                    socket.shutdownInput();

                    out.write(("服务端收到了你的数据").getBytes(charset));

                    // 模拟业务处理
                    Thread.sleep(100L);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
