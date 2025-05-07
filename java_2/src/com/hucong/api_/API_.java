package com.hucong.api_;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        //获取到本机的InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        //根据指定的主机名 获取InetAddress对象
        InetAddress host1 = InetAddress.getByName("LAPTOP-R0PKT4MA");
        System.out.println("host1 = " + host1);

        //根据域名返回 InetAddress对象 比如www.baidu.com
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2 = " + host2);

        //根据InetAddress对象获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println("host2 对应的IP地址 = " + hostAddress);

        //根据InetAddress对象获取对应的主机名或域名
        String hostName = host2.getHostName();
        System.out.println("host2 对应的主机名或域名 = " + hostName);
    }
}
