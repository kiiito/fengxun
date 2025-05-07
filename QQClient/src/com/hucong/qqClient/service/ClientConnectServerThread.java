package com.hucong.qqClient.service;

import com.hucong.qqClient.utils.Utility;
import com.hucong.qqCommon.Message;
import com.hucong.qqCommon.MessageType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnectServerThread extends Thread {
    private Socket socket;
    Scanner scanner = new Scanner(System.in);
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //因为线程需要在后台和服务器进行通讯 因此用while循环
        System.out.println("客户端线程 等待读取从服务器发送的消息");
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送message对象 线程将会堵塞在这里
                Message message = (Message) ois.readObject();
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    String[] s = message.getContent().split(" ");
                    System.out.println("\n=============当前在线用户列表=============");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户: " + s[i]);
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n" + message.getSendTime() + " " + message.getSender() + " 给你发送 " + message.getContent());
                } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    System.out.println("\n" + message.getSendTime() + " " + message.getSender() + " 给大家发送 " + message.getContent());
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    System.out.println("\n" + message.getSendTime() + " " + message.getSender() + " 给你发送文件到 " + message.getDest() );
                    //获取到文件输出流写到磁盘
                        FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                        fileOutputStream.write(message.getFileBytes());
                        //切记要关闭流 后期不在使用
                        fileOutputStream.close();
                    System.out.println("保存文件成功");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
