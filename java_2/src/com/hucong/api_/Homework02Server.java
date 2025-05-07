package com.hucong.api_;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Homework02Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        System.out.println("端口9999正在监听");
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);

        int len = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();
        String s = new String(data, 0, len);
        String answer = "";
        if ("四大名著有哪些".equals(s)){
            answer = "红楼梦，水浒传，西游记，三国演义";
        }else {
            answer = "what";
        }
        byte[] bytes1 = answer.getBytes();
        DatagramPacket packet1 =
                new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.31.1"), 9998);
        datagramSocket.send(packet1);

        datagramSocket.close();
    }
}
