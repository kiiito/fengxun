package com.hucong.interface_;

/**
 * 接口多态数组
 */
public class InterfacePolyArr {
    public static void main(String[] args) {
        //定义多态数组
        Usb []arr = new Usb[2];
        arr[0] = new Phone();
        arr[1] = new Camera();
        for (int i = 0; i <arr.length; i++) {
            arr[i].work();//动态绑定
            //判定类型 向下转型
            if(arr[i] instanceof Phone){
                ((Phone) arr[i]).call();
            }
        }
    }
}
interface Usb{
    void work();
}
class Phone implements Usb{
    public void call(){
        System.out.println("手机在打电话");
    }
    @Override
    public void work() {
        System.out.println("手机在工作");
    }
}
class Camera implements Usb{
    @Override
    public void work() {
        System.out.println("相机在工作");
    }
}