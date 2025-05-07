package com;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;
public class NationalStandardCodeConversion {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入内容: ");
            String input = scanner.nextLine(); // 读取用户输入
            scanner.close();

            try {
                // 将输入内容转换为 GB2312 编码的字节数组
                byte[] bytes = input.getBytes("GB2312");

                // 输出字节数组的十六进制值（两个字节为一组）
                System.out.println("十六进制输出（两个字节为一组）:");
                for (int i = 0; i < bytes.length; i++) {
                    // 将字节转换为无符号整数（0-255）并输出
                    System.out.printf("%02X", bytes[i] & 0xFF);

                    // 每两个字节加一个空格
                    if ((i + 1) % 2 == 0) {
                        System.out.print(" ");
                    }
                }

                // 如果字节数不是 2 的倍数，补充换行
                if (bytes.length % 2 != 0) {
                    System.out.println();
                }
            } catch (UnsupportedEncodingException e) {
                System.out.println("不支持 GB2312 编码！");
                e.printStackTrace();
            }
        }
    }

