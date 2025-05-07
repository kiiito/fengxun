package com.hucong.api_.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        // 1 连接服务端 IP 端口 连接成功 返回socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket 返回 = " + socket.getClass());
        // 2 连接成功 通过socket.getOutputStream();得到 和 socket对象相关的输出流对象 并写入数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("I love eula".getBytes());
        //必须设置结束标记 否则导致阻塞
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes))!= -1){
            System.out.println(new String(bytes,0,len));
        }
        //4 关闭流对象 和 socket 必须关闭
        outputStream.close();
        inputStream.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
