package com.hucong.qqClient.service;

import com.hucong.qqCommon.Message;
import com.hucong.qqCommon.MessageType;

import java.io.*;

public class FileClientServer {
    public void sendFileToOne(String src, String dst, String senderId, String getterId) throws IOException {
        Message message = new Message();
        message.setSender(senderId);
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSendTime(new java.util.Date().toString());
        message.setSrc(src);
        message.setDest(dst);
        message.setGetter(getterId);
        FileInputStream fileInputStream = null;
        File file = new File(src);
        if (!file.exists()) {
            System.out.println("目标文件不存在 请重新输入");
        } else {
            byte[] bytes = new byte[(int) new File(src).length()];
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(bytes);//将src文件读入到程序的字节数组
            //将文件对应的字节数组设置到message
            message.setFileBytes(bytes);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            System.out.println(getterId + " 给用户 " + getterId + " 发送文件 " + src);

            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        }
    }
}
