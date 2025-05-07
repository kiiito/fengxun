package com.hucong.extend_.exercise;

public class ExtendsExercise02 {
    public static void main(String[] args) {
        NotePad notePad = new NotePad("i9", 32, "西部世界", "外星人");
        notePad.info();
        Pc pc = new Pc("AMD R9", 32, "西部世界", "黑色");
        pc.info();
    }

}

class Computer {
    String cpu;
    int Memory;
    String HardDrive;

    public Computer(String cpu, int memory, String hardDrive) {
        this.cpu = cpu;
        this.Memory = memory;
        this.HardDrive = hardDrive;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return Memory;
    }

    public void setMemory(int memory) {
        this.Memory = memory;
    }

    public String getHardDrive() {
        return HardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.HardDrive = hardDrive;
    }

    public String getDetail() {
        return "cpu的名字" + cpu + "内存容量 " + Memory + "硬盘信息 " + HardDrive;
    }
}

//子类
class NotePad extends Computer {
    String brand;

    //IDEA自动将构造器调用写好
    public NotePad(String cpu, int memory, String hardDrive, String brand) {
        super(cpu, memory, hardDrive);
        this.brand = brand;
    }

    public void info() {
        System.out.println(getDetail() + "颜色" + brand);
    }
}

//子类
class Pc extends Computer {
    String color;

    public Pc(String cpu, int memory, String hardDrive, String color) {
        super(cpu, memory, hardDrive);
        this.color = color;
    }

    public void info() {
        System.out.println(getDetail() + "品牌" + color);
    }
}