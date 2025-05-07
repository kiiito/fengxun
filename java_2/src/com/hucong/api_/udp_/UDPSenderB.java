package com.hucong.api_.udp_;

import java.io.IOException;
import java.net.*;

public class UDPSenderB {
    //192.168.31.1
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket对象 准备在9998端口接受和发送数据
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        byte[] bytes = "I am deeply in love with you eula".getBytes();
        DatagramPacket datagramPacket =
                new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.31.1"), 9999);
        datagramSocket.send(datagramPacket);
        //构建一个DatagramPacket对象 在UDP中一个数据包最大64K
        byte[] bytes2 = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes2, bytes2.length);
        //调用接受方法 将通过网络传输的DatagramPacket对象填充到packet对象当中
        //当有数据发送到端口9999时就接受数据 没有就会阻塞等待
        datagramSocket.receive(datagramPacket1);

        //可以将datagramPacket进行拆包 取出数据并显示
        int len = datagramPacket1.getLength();//接受实际数据字节长度
        byte[] data = datagramPacket1.getData();//接受到数据
        String s = new String(data, 0, len);
        System.out.println(s);

        //关闭资源
        datagramSocket.close();
        System.out.println("B端口退出");
    }
}
