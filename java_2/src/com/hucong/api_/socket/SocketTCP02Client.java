package com.hucong.api_.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        // 1 连接服务端 IP 端口 连接成功 返回socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket 返回 = " + socket.getClass());
        // 2 连接成功 通过socket.getOutputStream();得到 和 socket对象相关的输出流对象 并写入数据
        OutputStream outputStream = socket.getOutputStream();
        // 使用转换流将字节流转换成字符流
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bfw.write("I love you eula");
        bfw.newLine();//插入一个换行符 表示内容结束 注意要求对方必须使用readLine()来读取数据
        bfw.flush();//如果使用字符流 需要手动刷新 否则数据不会写入数据通道
        InputStream inputStream = socket.getInputStream();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
        String line = bfr.readLine();
        System.out.println(line);
        //4 关闭流对象 和 socket 必须关闭
        bfw.close();
        bfr.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
