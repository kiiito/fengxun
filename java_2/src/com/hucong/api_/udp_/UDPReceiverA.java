package com.hucong.api_.udp_;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //创建一个DatagramSocket对象 准备在端口9999监听数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        System.out.println("正在监听9999端口");
        //构建一个DatagramPacket对象 在UDP中一个数据包最大64K
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        //调用接受方法 将通过网络传输的DatagramPacket对象填充到packet对象当中
        //当有数据发送到端口9999时就接受数据 没有就会阻塞等待
        datagramSocket.receive(datagramPacket);

        //可以将datagramPacket进行拆包 取出数据并显示
        int len = datagramPacket.getLength();//接受实际数据字节长度
        byte[] data = datagramPacket.getData();//接受到数据
        String s = new String(data, 0, len);
        System.out.printf(s);
        byte[] bytes1 = "I love you too".getBytes();
        DatagramPacket datagramPacket1 =
                new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.31.1"), 9998);
        datagramSocket.send(datagramPacket1);

        //关闭资源
        datagramSocket.close();
        System.out.println("端口A退出");
    }
}
