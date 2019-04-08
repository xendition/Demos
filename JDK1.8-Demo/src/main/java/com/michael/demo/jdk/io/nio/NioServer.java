package com.michael.demo.jdk.io.nio;

import com.michael.demo.jdk.io.AbstractIOConstant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO
 *
 * @author Michael
 */
public class NioServer extends AbstractIOConstant {

    private Selector selector;
    private ServerSocketChannel serverChannel;

    public void initNioServer() throws IOException {

        // 创建一个Selector
        this.selector = Selector.open();

        // 获取 ServerSocket 通道
        this.serverChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        this.serverChannel.configureBlocking(false);
        // 端口绑定
        this.serverChannel.bind(new InetSocketAddress(port));

        // 有连接访问进来，Selector 选中
        this.serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 开始监听
     */
    public void doListen() throws IOException {

        System.out.println("NIO 服务端启动，端口号为 : " + port);

        // 连接计数
        int connectionCount = 0;

        // 少量的线程
        int threadPoolSize = 6;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);

        while (true) {

            // 阻塞等待就绪的事件 select()方法为阻塞方法，此处不会导致CPU空转
            int readyCount = this.selector.select();
            // select()阻塞可能被中断，中断后返回值为0
            if (readyCount == 0) {
                continue;
            }

            Iterator<SelectionKey> keyIterator = this.selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                // 删除已选的key，防止重复处理
                keyIterator.remove();

                // 客户端请求连接事件
                if (key.isAcceptable()) {

                    // ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

                    // 获得与客户端连接的通道
                    SocketChannel channel = this.serverChannel.accept();
                    // 设置非阻塞
                    channel.configureBlocking(false);

                    // channel.write(ByteBuffer.wrap("恭喜你连接服务器成功".getBytes(charset)));

                    // 注册到selector 监听读就绪事件
                    channel.register(this.selector, SelectionKey.OP_READ, ++connectionCount);

                } else if (key.isConnectable()) {

                } else if (key.isReadable()) {

                    // 数据可以进行读取了，交给线程池处理
                    threadPool.execute(new SocketProcess(key));

                    // 取消selector注册，防止线程处理不及时，重复选择
                    key.cancel();
                } else if (key.isWritable()) {

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        NioServer server = new NioServer();
        server.initNioServer();
        server.doListen();
    }

    static class SocketProcess implements Runnable {

        private SelectionKey key;

        public SocketProcess(SelectionKey key) {
            super();
            this.key = key;
        }

        @Override
        public void run() {
            try {

                System.out.println(
                        System.currentTimeMillis() + " 连接" + this.key.attachment() + "发来了信息：" + this.readFromChannel());
                // 连接不需要了就关闭
                this.key.channel().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String readFromChannel() throws IOException {

            SocketChannel channel = (SocketChannel) this.key.channel();

            int bufferSize = 1024;
            ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

            // 定义一个更大的Buffer
            ByteBuffer bigBuffer = null;

            int count = 0;
            while ((channel.read(buffer)) != -1) {
                count++;

                ByteBuffer tempBuffer = ByteBuffer.allocateDirect(bufferSize * (count + 1));
                if (bigBuffer != null) {
                    // 将buffer由写模式转为读模式
                    bigBuffer.flip();
                    tempBuffer.put(bigBuffer);
                }

                bigBuffer = tempBuffer;

                // 将本次读到的数据放入BigBuffer中
                buffer.flip();
                bigBuffer.put(buffer);

                // 为下一次读，清理buffer
                buffer.clear();
            }

            if (bigBuffer != null) {

                bigBuffer.flip();

                return decoder.decode(bigBuffer).toString();
            }

            return null;
        }
    }
}
