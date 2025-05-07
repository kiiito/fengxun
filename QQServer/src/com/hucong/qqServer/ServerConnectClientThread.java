package com.hucong.qqServer;

import com.hucong.qqCommon.Message;
import com.hucong.qqCommon.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;
    private ArrayList<Message> list = new ArrayList<>();

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("服务端和客户端" + userId + "保持通信 读取数据");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //获取到客户端发送来的message对象
                Message msg = (Message) ois.readObject();
                //判断message对象的类型是否是要求返回用户列表
                if (msg.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
                    System.out.println(msg.getSender() + "请求在线用户列表");
                    //获取当前在线用户列表
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    //返回message
                    Message message = new Message();
                    message.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message.setContent(onlineUser);
                    message.setGetter(message.getSender());
                    //返回给客户端
                    oos.writeObject(message);
                }else if (msg.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println(msg.getSender() + " 退出");
                    //将退出的线程从集合中移除
                    ManageClientThreads.removeServerConnectClientThread(msg.getSender());
                    socket.close();//关闭连接
                    break;//退出
                }else if (msg.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    System.out.println(msg.getSendTime() + msg.getSender() + " 正在向用户 " + msg.getGetter() + " 发送信息");
                    System.out.println("发送的信息: " + msg.getContent());
                    /**
                     * 这里可以扩展成离线保存在数据库中
                     */
                    ServerConnectClientThread serverConnectClientThread =
                            ManageClientThreads.getServerConnectClientThread(msg.getGetter());
                    if(serverConnectClientThread == null){
                        System.out.println("用户离线");
                        list.add(msg);
                        QQServer.getOffLineDb().put(msg.getGetter(),list);
//                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/com/hucong/text/text.txt"));
//                        oos.writeObject(QQServer.getOffLineDb());
//                        oos.close();
                    }else {
                        ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos.writeObject(msg);
                    }
                } else if (msg.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    System.out.println(msg.getSendTime() + msg.getSender() + "正在向大家发送 " + msg.getContent());
                    //获取到线程的集合
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                    //遍历线程集合的key
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        //获取到集合的key 也就是用户名
                        String onLineUserId = iterator.next();
                        //判断是否是是否是自己 不是则发送消息
                        if (!onLineUserId.equals(msg.getSender())){
                            //通过线程集合获取到集合中各个线程的socket 再获取对应的输出流
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(onLineUserId).getSocket().getOutputStream());
                            oos.writeObject(msg);
                        }
                    }
                } else if (msg.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    System.out.println(msg.getSendTime() + msg.getSender() + " 正在向用户 " + msg.getGetter() + " 发送文件");
                    ServerConnectClientThread serverConnectClientThread =
                            ManageClientThreads.getServerConnectClientThread(msg.getGetter());
                    if (serverConnectClientThread == null){
                        System.out.println("用户离线");
                        list.add(msg);
                        QQServer.getOffLineDb().put(msg.getGetter(),list);
                    }else{
                        ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos.writeObject(msg);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
            }
        }
    }
}
