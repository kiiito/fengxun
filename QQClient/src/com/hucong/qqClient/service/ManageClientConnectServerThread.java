package com.hucong.qqClient.service;

import java.util.HashMap;

public class ManageClientConnectServerThread {
     private static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();
     //将某个线程加入到集合当中
     public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread) {
         hm.put(userId,clientConnectServerThread);
     }
     //通过userId 得到对应的线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
         return hm.get(userId);
    }
}
