package com.hucong.qqClient.service;

import com.hucong.qqCommon.Message;
import com.hucong.qqCommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageClientService {
    public void sendMessage(String content, String sendId, String getterId){
        Message message = new Message();
        message.setContent(content);
        message.setSender(sendId);
        message.setGetter(getterId);
        message.setSendTime(new java.util.Date().toString());
        System.out.println(sendId + " Ïò " + getterId + " ·¢ËÍ " + content);
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread(sendId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }

