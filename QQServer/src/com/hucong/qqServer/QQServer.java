package com.hucong.qqServer;

import com.hucong.qqCommon.Message;
import com.hucong.qqCommon.MessageType;
import com.hucong.qqCommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class QQServer {
    private ServerSocket ss = null;
    //ConcurrentHashMap 可以处理并发的集合 处理了线程安全 实现了线程同步
    private static ConcurrentHashMap<String,User> hashMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ArrayList<Message>> offLineDb = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, ArrayList<Message>> getOffLineDb() {
        return offLineDb;
    }

    static{
        hashMap.put("甘雨",new User("甘雨","123456"));
        hashMap.put("尤拉",new User("尤拉","123456"));
        hashMap.put("雷电将军",new User("雷电将军","123456"));
        hashMap.put("刻晴",new User("刻晴","123456"));
    }
    public boolean checkUser(String userId, String password){
        User user1 = hashMap.get(userId);
       // System.out.println(user1);
        if (user1 == null) return false;
        if(!(user1.getPassword().equals(password))){
            return false;
        }
        return true;
    }
    public QQServer(){
        try {
            System.out.println("服务端在9999端口监听");
            ss = new ServerSocket(9999, 50, InetAddress.getByName("0.0.0.0"));
            new Thread(new SendNewsToAll()).start();
            while (true){
                Socket socket = ss.accept();//如果没有客户端连接 就会阻塞在这里
                //得到socket关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //得到socket关联的对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User u = (User)ois.readObject();
                //创建message对象 准备回复客户端
                Message message = new Message();

                if (checkUser(u.getUserId(), u.getPassword())){
                    //登入通过 设置登入状态
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCESS);
                    //将message回复给客户端
                    oos.writeObject(message);
                    //创建一个线程 和客户端保持通信 该线程持有socket对象
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket,u.getUserId());
                    //启动线程
                    serverConnectClientThread.start();
                    //把该线程对象放入到一个集合中 进行管理
                    ManageClientThreads.addServerConnectClientThread(u.getUserId(),serverConnectClientThread);
                    Iterator<String> iterator = offLineDb.keySet().iterator();
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        if (next.equals(u.getUserId())){
                            ArrayList<Message> messages = offLineDb.get(next);
                            for (int i = 0; i < messages.size(); i++) {
                                Message message1 = messages.get(i);
                                System.out.println(message1.getSendTime() + " " + message1.getSender() + " 给你留言 " + message1.getContent());
                                ObjectOutputStream oos1 = new ObjectOutputStream(socket.getOutputStream());
                                oos1.writeObject(message1);
                            }
                            offLineDb.remove(messages);
                        }
                    }

                }  else {
                    //登入失败
                    System.out.println("用户id = " + u.getUserId() + " pwd = " + u.getPassword() + " 验证失败 ");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //如果服务端退出while循环说明服务端不在监听 因此需要关闭serverSocket
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
