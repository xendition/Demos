package com.michael.demo.jdk.udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

/**
 * @author Michael
 */
public class UdpSendDemo {

    public static void main(String[] args) throws InterruptedException {

        try (
                /** 1、建立udp socket端点 */
                DatagramSocket datagramSocket = new DatagramSocket()
        ) {

            /** 2、提供数据，封装打包  ---DatagramPacket(byte[] buf, int length, InetAddress address, int port)  */
            byte[] bs = "正在使用UDP发送 -- 我是数据! ".getBytes(Charset.forName("UTF-8"));
            DatagramPacket datagramPacket = new DatagramPacket(
                    bs, bs.length, InetAddress.getByName("192.168.127.174"), 1111);

            /** 3、使用send发送 */

            datagramSocket.send(datagramPacket);

            // while (true) {
            //
            //     datagramSocket.send(datagramPacket);
            //     Thread.sleep(2000L);
            // }
        } catch (IOException e) {
            System.out.println("发送失败： ");
            e.printStackTrace();
        }
    }
}
