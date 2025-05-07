package com.hucong.api_.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCopyClient {
    public static void main(String[] args)throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //获取图片输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\1.jpg"));
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        int readDate = 0;
        while ((readDate = bis.read(bytes)) != -1) {
            //将图片的数据写入到数据通道之中
            outputStream.write(bytes,0,readDate);
        }
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        System.out.println(line);
        outputStream.close();
        bufferedReader.close();
        bis.close();
        socket.close();
    }
}
