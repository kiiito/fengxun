package com.hucong.api_;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Homework01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("¶Ë¿Ú7777¼àÌýÖÐ");
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        if("name".equals(s)){
            bufferedWriter.write("hu");
        }else if("hobby".equals(s)){
            bufferedWriter.write("±àÐ´Java");
        }else {
            bufferedWriter.write("ÄãËµÉ¶ÄØ");
        }
        bufferedWriter.newLine();
        bufferedWriter.flush();

        //¹Ø±Õ
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
