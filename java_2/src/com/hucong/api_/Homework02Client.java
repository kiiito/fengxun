package com.hucong.api_;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class Homework02Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你想要问的问题");
        String question = scanner.next();
        byte[] bytes = question.getBytes();
        DatagramPacket datagramPacket =
                new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.31.1"), 9999);
        datagramSocket.send(datagramPacket);
        byte[] bytes1 = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1, bytes1.length);
        datagramSocket.receive(datagramPacket1);

        int len = datagramPacket1.getLength();
        byte[] data = datagramPacket1.getData();
        String s = new String(data, 0, len);
        System.out.println(s);
        datagramSocket.close();
    }
}
