package com.hucong.houserent.view;

import com.hucong.houserent.domain.House;
import com.hucong.houserent.service.HouseService;
import com.hucong.houserent.utils.Utility;

/**
 * 显示界面，接受用户输入，调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    private   HouseService houseService  = new HouseService(10);
    public void  listHouse(){
        System.out.println("-----------房屋列表-----------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();//得到房屋所有信息
        //遍历house数组
        for (int i = 0; i < houses.length; i++) {
            if(houses[i] != null){
                System.out.println(houses[i].toString());
            }
        }
        System.out.println("---------房屋列表显示完毕---------\n");
    }

    //编写addHouse() 接受输入，创建House对象，调用add方法
    public void addHouse(){
        System.out.println("-----------增加房屋-----------");
        System.out.print("姓名: ");
        String name = Utility.readString(8);
        System.out.print("电话: ");
        String phone = Utility.readString(12);
        System.out.print("地址: ");
        String address = Utility.readString(16);
        System.out.print("月租: ");
        int rent = Utility.readInt();
        System.out.print("状态: ");
        String state = Utility.readString(3);

        //创建一个新的House对象,id是系统分配的用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if(houseService.add(newHouse)){
            System.out.println("-----------添加房屋成功-----------\n");
        }else {
            System.out.println("-----------添加房屋失败-----------\n");
        }
    }
    //编写delHouse() 接受输入的id ，调用Service的del方法
    public void delHouse(){
        System.out.println("-----------删除房屋信息-----------");
        System.out.print("请输入待删除的房屋编号(-1退出):");
        int delIn = Utility.readInt();
        if(delIn == -1){
            System.out.println("-----------放弃删除房屋信息-----------");
            return;
        }
        //注意该方法本身就有循环判断的逻辑，必须输入y或n
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y'){
            if(houseService.del(delIn)){
                System.out.println("-----------删除房屋信息成功-----------");
            }else {
                System.out.println("-----房间编号不存在,删除房屋信息失败---");
            }
        }else {
            System.out.println("-----------放弃删除房屋信息-----------");
        }
    }
    //q确认完成退出功能
    public void exit() {
        char c = Utility.readConfirmSelection();
        if(c == 'Y'){
            loop = false;
        }
    }
    //查找房屋信息
    public void seekHouse(){

        System.out.println("-----------查找房屋信息-----------\n");
        System.out.print("请输入你要查找的房间号:");
        int seekIn = Utility.readInt();
//        House[] houses = houseService.list();
//        if (houseService.seek(seekIn) != -1){
//            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
//            System.out.println(houses[houseService.seek(seekIn)].toString());
//        }else {
//            System.out.println("你输入的房间号不存在");
//        }
        if (houseService.seek(seekIn) != null){
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
            System.out.println(houseService.seek(seekIn).toString());
        }else {
            System.out.println("你输入的房间号不存在");
        }
    }

    //修改房屋信息
    public void modifyHouse(){
        System.out.println("-----------修改房屋信息-----------\n");
        System.out.print("请选择待修改的房屋编号(-1退出):");
        int modifyIn = Utility.readInt();
        if(modifyIn == -1){
            System.out.println("-----------你放弃修改房屋信息-----------\n");
            return;
        }
            House house = houseService.seek(modifyIn);
        if(house == null){
            System.out.print("未查找到房屋信息，无法修改");
        }
            System.out.print("姓名(" + house.getName() + "):");
            String name = Utility.readString(8,"");
            if(!"".equals(name)){
                house.setName(name);
            }
        System.out.print("电话(" + house.getPhone() + "):");
            String phone = Utility.readString(12,"");
        if(!"".equals(phone)){
            house.setPhone(phone);
        }
        System.out.print("地址(" + house.getAddress() + "):");
        String address = Utility.readString(16,"");
        if(!"".equals(address)){
            house.setAddress(address);
        }
        System.out.print("月租(" + house.getRent() + "):");
        int rent = Utility.readInt(-1);
        if(rent != -1){
            house.setRent(rent);
        }
        System.out.print("状态(" + house.getState() + "):");
        String state = Utility.readString(3,"");
        if(!"".equals(state)){
            house.setState(state);
        }
    }
    public void mainMenu(){
        do{
            System.out.println("-----------房屋出租系统-----------");
            System.out.println("\t\t 1 新 增 房 源");
            System.out.println("\t\t 2 查 找 房 屋");
            System.out.println("\t\t 3 删 除 房 屋");
            System.out.println("\t\t 4 修 改 房 屋 信 息");
            System.out.println("\t\t 5 房 屋 列 表");
            System.out.println("\t\t 6 退    出");
            System.out.println("请输入你的选择 1-6");
            key = Utility.readChar();
            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    seekHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    modifyHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exit();
                    break;
            }
        }while (loop);
    }
}
