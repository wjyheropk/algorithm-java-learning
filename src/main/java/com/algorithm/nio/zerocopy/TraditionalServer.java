package com.algorithm.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统方式的文件传输服务端
 */
public class TraditionalServer {

    public static void main(String args[]) {

        int port = 2000;
        ServerSocket serverSocket;
        DataInputStream input;

        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Server waiting for client on port " + serverSocket.getLocalPort());

            // server infinite loop
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
                input = new DataInputStream(socket.getInputStream());
                // print received data
                try {
                    byte[] byteArray = new byte[4096];
                    while (true) {
                        int nread = input.read(byteArray, 0, 4096);
                        if (-1 == nread) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // connection closed by client
                try {
                    socket.close();
                    System.out.println("Connection closed by client");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
