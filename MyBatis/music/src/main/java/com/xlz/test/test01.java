package com.xlz.test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        System.out.println("测试中文输出");
        System.out.println("Default Charset: " + Charset.defaultCharset());
        String url = "jdbc:mysql://localhost:3306/mvc?useUnicode=true&characterEncoding=UTF-8";
        try (Connection conn = DriverManager.getConnection(url, "root", "hc");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT song_name FROM m_song")) {

            while (rs.next()) {
                String songName = rs.getString(1);
                System.out.println("数据库原始数据: " + songName);
                System.out.println("字节表示: " + Arrays.toString(songName.getBytes(StandardCharsets.UTF_8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
