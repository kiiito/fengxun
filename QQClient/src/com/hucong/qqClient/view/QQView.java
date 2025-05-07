package com.hucong.qqClient.view;

import com.hucong.qqClient.service.FileClientServer;
import com.hucong.qqClient.service.MessageClientService;
import com.hucong.qqClient.service.UserClientService;
import com.hucong.qqClient.utils.Utility;

import java.io.IOException;

public class QQView {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new QQView().mainMenu();
    }

    private boolean loop = true;
    private String key = "";
    private UserClientService userClientService = new UserClientService();//用于登入服务器
    private MessageClientService messageClientService = new MessageClientService();
    private FileClientServer fileClientServer = new FileClientServer();
    private void mainMenu() throws IOException, ClassNotFoundException {
        while (loop) {
            System.out.println("=============欢迎登录网络通信系统===============");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);

            switch (key) {
                case "1":
                    System.out.print("请输入用户号:");
                    String useId = Utility.readString(50);
                    System.out.print("请输入密 码:");
                    String password = Utility.readString(50);

                    if (userClientService.checkUser(useId, password)) {
                        System.out.println("==========欢迎用户 (" + useId + ")登入成功 ==============");
                        while (loop) {
                            System.out.println("==========网络通信系统二级菜单(用户 " + useId + " )=================");
                            System.out.println("1 显示在线用户列表");
                            System.out.println("2 群发消息");
                            System.out.println("3 私聊消息");
                            System.out.println("4 发送文件");
                            System.out.println("9 退出系统");
                            System.out.println("请输入你的选择");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入您想要与大家发送的消息:");
                                    String takeAll = Utility.readString(100);
                                    userClientService.sendMessageToAll(takeAll,useId);
                                    break;
                                case "3":
                                    System.out.print("请输入你要发送的人:");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入你要发送的信息:");
                                    String content = Utility.readString(100);
                                    messageClientService.sendMessage(content,useId,getterId);
                                    break;
                                case "4":
                                    System.out.print("请输入想要发送文件的用户:");
                                     getterId = Utility.readString(50);
                                    System.out.print("请输入发送文件的路径(形式如 : D:\\\\xx.jpg)");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入你要发送到对方的路径(形式如 : D:\\\\xx.jpg): ");
                                    String dst = Utility.readString(100);
                                    fileClientServer.sendFileToOne(src,dst,useId,getterId);
                                    break;
                                case "9":
                                    loop = false;
                                    userClientService.logout();
                                    System.out.println("退出系统");
                                    break;

                            }
                        }
                    } else {
                        System.out.println("登陆服务器失败");
                    }
                    break;
                case "2":
                    loop = false;
                    System.out.println("退出系统");
                    break;
            }
        }
    }
}
