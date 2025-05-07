package com.hucong.qqClient.service;
import com.hucong.qqClient.utils.Utility;
import com.hucong.qqCommon.Message;
import com.hucong.qqCommon.MessageType;
import com.hucong.qqCommon.User;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class UserClientService {
    private User u = new User();
    private Socket socket;
    public boolean checkUser(String userId, String pwd) throws IOException, ClassNotFoundException {
        boolean b = false;
        u.setUserId(userId);
        u.setPassword(pwd);
        //连接服务器 发送u对象
        socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        ObjectOutputStream obs = new ObjectOutputStream(socket.getOutputStream());
        obs.writeObject(u);
        //obs.close();

        //读取从服务器回复的Message对象
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message) ois.readObject();
        if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
            ClientConnectServerThread cst = new ClientConnectServerThread(socket);
            //启动客户端线程
            cst.start();
            //客户端扩展 将线程放入集合管理
            ManageClientConnectServerThread.addClientConnectServerThread(userId,cst);
            b = true;
        }else {
            //如果登陆失败 就不能启动线程
           socket.close();
        }
        return b;
    }
    public void onlineFriendList(){
        //发送一个message对象
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());
        try {
            //得到当前线程的socket对应的ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void logout(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserId() + " 退出系统");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessageToAll(String content, String senderId){
        Message message = new Message();
        message.setContent(content);
        message.setSender(senderId);
        message.setSendTime(new java.util.Date().toString());
        System.out.println(senderId + " 向大家发送 " + content);
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
