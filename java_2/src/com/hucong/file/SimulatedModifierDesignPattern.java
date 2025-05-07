package com.hucong.file;

public class SimulatedModifierDesignPattern {
    public static void main(String[] args) {
        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.fileReaders(5);
        BufferedReader_ bufferedReader_1 = new BufferedReader_(new StringReader_());
        bufferedReader_1.stringReaders(10);

    }
}
 abstract class Reader_{
    public void stringReader(){}
     public void fileReader(){}
 }
 class StringReader_ extends Reader_{
     @Override
     public void stringReader() {
         System.out.println("字符流读取中");
     }
 }
 class FileReader_ extends Reader_{
     @Override
     public void fileReader() {
         System.out.println("文件流读取中");
     }
 }
 class BufferedReader_ extends Reader_{
    private Reader_ reader_;//属性是Reader_类型
    //可接受Reader_的子类
     public BufferedReader_(Reader_ reader_) {
         this.reader_ = reader_;
     }
     //扩展方法 多次读取文件 或者是加缓冲char[]等等
     public void fileReaders(int num){
         for (int i = 0; i < num; i++) {
             reader_.fileReader();
         }
     }
     public void stringReaders(int num){
         for (int i = 0; i < num; i++) {
             reader_.stringReader();
         }
     }
 }