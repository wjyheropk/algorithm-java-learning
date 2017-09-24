package com.java.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用{@code FileChannel.transferTo}零拷贝方式的文件传输客户端。
 * 实测结果，100M大小的文件，执行时间减少66%左右
 */
public class TransferToClient {

    public static void main(String[] args) throws IOException {
        TransferToClient sfc = new TransferToClient();
        sfc.testSendFile();
    }

    public void testSendFile() throws IOException {
        String host = "localhost";
        int port = 9026;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);
        String fileName = "/Users/wangjiayin/Downloads/Netty权威指南 第2版 带书签目录 完整版.pdf";

        FileChannel fc = new FileInputStream(fileName).getChannel();
        long start = System.currentTimeMillis();
        long nsent = 0, curnset = 0;
        curnset = fc.transferTo(0, fc.size(), sc);
        long end = System.currentTimeMillis();
        System.out.println("total bytes transferred--" + curnset + " and time taken in MS--" + (end - start));
    }

}
