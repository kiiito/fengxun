package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 设备类
class Device {
    String name;
    String type;
    String status; // 0 表示空闲，1 表示忙
    String controller;
    String channel;
    String dct;

    public Device(String name, String type, String controller, String channel, String dct) {
        this.name = name;
        this.type = type;
        this.status = "0"; // 默认空闲
        this.controller = controller;
        this.channel = channel;
        this.dct = dct;
    }

    @Override
    public String toString() {
        return "设备名称: " + name + ", 进程: null, 设备类型: " + type + ", 状态: " + status + ", 控制器: " + controller + ", 通道: " + channel + ", 状态设备控制表: " + dct;
    }
}

// 控制器类
class Controller {
    String name;
    String status; // 0 表示空闲，1 表示忙
    List<String> chcts;
    List<String> controllersQueue;

    public Controller(String name) {
        this.name = name;
        this.status = "0"; // 默认空闲
        this.chcts = new ArrayList<>();
        this.controllersQueue = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "控制器标识符: " + name + ", 控制器状态: " + status + ", CHCT指针: " + String.join(", ", chcts) + ", 控制器等待队列指针: " + String.join(", ", controllersQueue);
    }
}

// 通道类
class Channel {
    String name;
    String status; // 0 表示空闲，1 表示忙
    String chct;
    List<String> channelsQueue;

    public Channel(String name) {
        this.name = name;
        this.status = "0"; // 默认空闲
        this.chct = "";
        this.channelsQueue = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "通道标识符: " + name + ", 通道状态: " + status + ", 通道等待队列指针: " + String.join(", ", channelsQueue);
    }
}

// 设备管理系统类
class DeviceManagementSystem {
    Map<String, Device> devices = new HashMap<>();
    Map<String, Controller> controllers = new HashMap<>();
    Map<String, Channel> channels = new HashMap<>();

    public void addDevice(String name, String type, String controller, String channel, String dct) {
        Device device = new Device(name, type, controller, channel, dct);
        devices.put(name, device);
    }

    public void removeDevice(String name) {
        devices.remove(name);
    }

    public void addController(String name) {
        Controller controller = new Controller(name);
        controllers.put(name, controller);
    }

    public void removeController(String name) {
        controllers.remove(name);
    }

    public void addChannel(String name) {
        Channel channel = new Channel(name);
        channels.put(name, channel);
    }

    public void removeChannel(String name) {
        channels.remove(name);
    }

    public void allocateDevice(String processName, String deviceName) {
        Device device = devices.get(deviceName);
        if (device != null && device.status.equals("0")) {
            device.status = "1";
            System.out.println("设备 " + deviceName + " 分配给进程 " + processName);
        } else {
            System.out.println("设备 " + deviceName + " 无法分配");
        }
    }

    public void releaseDevice(String deviceName) {
        Device device = devices.get(deviceName);
        if (device != null && device.status.equals("1")) {
            device.status = "0";
            System.out.println("设备 " + deviceName + " 已回收");
        } else {
            System.out.println("设备 " + deviceName + " 无法回收");
        }
    }

    public void printDevices() {
        System.out.println("设备名称\t进程\t设备类型\t状态\t设备控制器\t通道\t状态设备控制表");
        for (Device device : devices.values()) {
            System.out.println(device);
        }
    }

    public void printControllers() {
        System.out.println("控制器标识符\t控制器状态\tCHCT指针\t控制器等待队列指针");
        for (Controller controller : controllers.values()) {
            System.out.println(controller);
        }
    }

    public void printChannels() {
        System.out.println("通道标识符\t通道状态\t通道等待队列指针");
        for (Channel channel : channels.values()) {
            System.out.println(channel);
        }
    }

    public static void main(String[] args) {
        DeviceManagementSystem dms = new DeviceManagementSystem();

        dms.addDevice("鼠标1", "鼠标", "controller1", "channel1", "DCT");
        dms.addDevice("键盘1", "键盘", "controller1", "channel1", "DCT");
        dms.addDevice("打印机1", "打印机", "controller2", "channel1", "DCT");
        dms.addDevice("显示器1", "显示器", "controller3", "channel2", "DCT");

        dms.addController("controller1");
        dms.addController("controller2");
        dms.addController("controller3");

        dms.addChannel("channel1");
        dms.addChannel("channel2");
        dms.addChannel("ch1");

        dms.printDevices();
        dms.printControllers();
        dms.printChannels();

        dms.allocateDevice("进程1", "鼠标1");
        dms.releaseDevice("鼠标1");
    }
}