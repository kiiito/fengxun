package com.hucong.api_.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP02Server {
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
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
        String line = bfr.readLine();
        System.out.println(line);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("I love you too");
        bw.newLine();//插入一个换行符 表示内容结束 注意要求对方必须使用readLine()来读取数据
        bw.flush();//如果使用字符流 需要手动刷新 否则数据不会写入数据通道
        //关闭流和socket和serverSocket
        bfr.close();
        bw.close();
        socket.close();
        serverSocket.close();

    }
}
