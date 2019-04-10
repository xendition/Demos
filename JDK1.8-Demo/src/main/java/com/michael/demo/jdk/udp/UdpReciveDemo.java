package com.michael.demo.jdk.udp;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Michael
 */
public class UdpReciveDemo {

    public static void main(String[] args) throws Exception {
        /**1、建立udp socket，设置接收端口*/

        DatagramSocket ds = new DatagramSocket(1111);

        /**2、预先创建数据存放的位置，封装*/
        byte[] bbuf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length);

        /**3、使用receive阻塞式接收*/

        while (true) {
            ds.receive(dp);

            System.out.println("ip::" + dp.getAddress().getHostAddress() + "\nport::" + dp.getPort() + "\ndata::" +
                               doFilter(dp.getData()));
        }

        /**4、关闭资源*/
        // ds.close();
    }

    private static String doFilter(byte[] bytes) throws UnsupportedEncodingException {

        return new String(bytes, "UTF-8").replaceAll("[\u0000]", "");
    }
}
