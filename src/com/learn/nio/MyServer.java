package com.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * nio练习
 *
 * @author suvue
 * @date 2020/5/13
 */
public class MyServer {
    private int size = 1024;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ByteBuffer byteBuffer;
    private int remoteClientNum;

    public MyServer(int port) {
        try {
            initChannel(port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void initChannel(int port) throws IOException {
        //打开通道
        serverSocketChannel = ServerSocketChannel.open();
        //设为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        //打开选择器
        selector = Selector.open();
        //注册选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //分配缓冲区的大小
        byteBuffer = ByteBuffer.allocate(size);
    }

    public void listener() throws IOException {
        while (true) {
            //返回的int值表示有多少个channel处于就绪状态
            final int n = selector.select();
            if (n == 0) {
                continue;
            }
            //每个selector对应多个selectedKey,每个selectedKey对应一个channel
            final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                final SelectionKey key = iterator.next();
                //如果SelectKey处于连接就绪状态，则开始接受客户端的连接
                if (key.isAcceptable()) {
                    //获取Channel
                    final ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    //channel接受连接
                    final SocketChannel channel = server.accept();
                    //channel注册
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    //远程客户端的连接数统计
                    remoteClientNum++;
                    System.out.println("online client num = " + remoteClientNum);
                    write(channel, "hello client".getBytes());
                }
                //如果通道已经处于读就绪状态，则读取通道上的数据
                if (key.isReadable()) {
                    read(key);
                }
                iterator.remove();
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        final SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        //从通道中读取数据到缓冲区
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            //byteBuffer写模式变为读模式
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
        }
        if (count < 0) {
            socketChannel.close();
        }

    }

    private void write(SocketChannel channel, byte[] writeData) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(writeData);
        //byteBuffer从写模式变成读模式
        byteBuffer.flip();
        //将缓冲区的数据写道通道中
        channel.write(byteBuffer);
    }

    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }

    public static void main(String[] args) {
        final MyServer myServer = new MyServer(9999);
        try {
            myServer.listener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
