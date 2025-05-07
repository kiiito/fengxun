package com.hucong.collection_.set_;

public class HashSetStructure {
    public static void main(String[] args) {
        //创建一个数组 数组类型 是Node[]
        Node[] nodes = new Node[16];
        Node jack = new Node("jack",null);
        nodes[2] =jack;
        Node hu = new Node("hu",null);
        jack.next = hu;
        Node ganYu = new Node("ganYu",null);
        hu.next = ganYu;
//        nodes[2] = new Node("jack",null);
//        nodes[2].next = new Node("hu",null);
//        nodes[2].next.next = new Node("ganYU",null);
        System.out.println("nodes =" + nodes);
    }
}
class Node{//节点 存储数据 可以指向下一个节点
    Object item;//存放数据
    Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }
}