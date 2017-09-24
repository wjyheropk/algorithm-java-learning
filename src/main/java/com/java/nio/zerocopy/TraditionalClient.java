package com.java.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 传统方式的文件传输客户端
 */
public class TraditionalClient {

    public static void main(String[] args) {

        int port = 2000;
        String server = "localhost";
        Socket socket = null;
        String lineToBeSent;

        DataOutputStream output = null;
        FileInputStream inputStream = null;
        int ERROR = 1;

        // connect to server
        try {
            socket = new Socket(server, port);
            System.out.println("Connected with server " + socket.getInetAddress() + ":" + socket.getPort());
        } catch (IOException e) {
            System.out.println(e);
            System.exit(ERROR);
        }

        try {
            String fname = "/Users/wangjiayin/Downloads/Netty权威指南 第2版 带书签目录 完整版.pdf";
            inputStream = new FileInputStream(fname);

            output = new DataOutputStream(socket.getOutputStream());
            long start = System.currentTimeMillis();
            byte[] b = new byte[4096];
            long read = 0, total = 0;
            while ((read = inputStream.read(b)) >= 0) {
                total = total + read;
                output.write(b);
            }
            long end = System.currentTimeMillis();
            System.out.println("bytes send--" + total + " and totaltime--" + (end - start));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            output.close();
            socket.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
