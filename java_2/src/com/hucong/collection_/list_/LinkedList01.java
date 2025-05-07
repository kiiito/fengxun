package com.hucong.collection_.list_;

public class LinkedList01 {
    public static void main(String[] args) {
        //模拟一个简单的双向链表
        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node eula = new Node("eula");

        // 连接三个节点
        //jack -> tom -> eula
        jack.next = tom;
        tom.next = eula;
        // eula -> tom -> jack
        eula.pre = tom;
        tom.pre = jack;

        //设置头节点和尾节点
        Node first = jack;
        Node last = eula;
//        //eula -> jack jack ->eula 形成闭环
//        eula.next = jack;
//        jack.pre =eula;
        //遍历对象
        while (true){
            if (first == null){
                break;
            }
            System.out.println(first);
            first = first.next;
        }
        //链表添加对象 在tom  eula 之间插入一个对象 GanYu
        Node ganYu = new Node("GanYu");
        tom.next = ganYu;
        ganYu.pre = tom;
        ganYu.next =eula;
        eula.pre = ganYu;
        System.out.println("==================");

        //重置头节点和尾节点
        first = jack;
         last = eula;
        while (true){
            if (first == null){
                break;
            }
            System.out.println(first);
            first = first.next;
        }
    }
}

//定义一个 Node类 Node 对象 表示双向链表的一个节点
class Node{
    public Object item;//data 存放数据
    public Node next; //指向下一个节点 指针
    public Node pre;// 指向前一个节点

    public Node(Object name) {
        this.item = name;
    }

    @Override
    public String toString() {
        return "Node name =" + item;
    }
}