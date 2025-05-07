package com.hucong.api_.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketCopyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("正在监听端口8888 等待连接");
        //获取图片要写入的地址的输出流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/com/hucong/api_/socket/1.jpg"));
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readDate = 0;
        while ((readDate = inputStream.read(bytes)) != -1) {
            //将由服务端传来的数据写入到本地
            bos.write(bytes, 0, readDate);
        }
        System.out.println("传输成功");
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("收到图片");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bos.close();
        inputStream.close();
        bufferedWriter.close();
        socket.close();
        serverSocket.close();
    }
}
