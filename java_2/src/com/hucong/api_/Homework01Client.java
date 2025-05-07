package com.hucong.api_;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Homework01Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),7777);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //bufferedWriter.write("name");
        bufferedWriter.write("hobby");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        //¹Ø±Õ
        socket.close();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
