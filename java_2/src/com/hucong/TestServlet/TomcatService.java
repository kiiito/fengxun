package com.hucong.TestServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TomcatService {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/hucong/TestServlet/web.properties"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要访问的Java程序对应的配置名");
        String s = scanner.nextLine();
        String property = properties.getProperty(s);
        Class aClass = Class.forName(property);
        Object obj = aClass.newInstance();
        ServletService servlet = (ServletService) obj;
        servlet.service();
    }
}
