package com.hucong.api_.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        // 1 在本机的9999 端口监听 等待连接
        // 细节：要求在本机没有其他服务在监听9999
        // 细节：这个ServerSocket 可以通过accept() 返回多个socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器 在端口9999监听 等待连接");
        // 2 当没有客户端连接9999端口时 程序会发生堵塞 等待连接
        // 如果有客户端连接 则会返回socket对象 程序继续
        Socket socket = serverSocket.accept();
        // 3 通过socket.getInputStream() 读取客户端写入数据通道的数据 并显示出来
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes,0,readLen));
        }
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("I love you too".getBytes());
        //设置结束标记
        socket.shutdownOutput();
        //关闭流和socket和serverSocket
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();

    }
}
