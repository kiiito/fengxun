package com.hucong.mhl.view;

import com.hucong.mhl.domain.*;
import com.hucong.mhl.service.BillService;
import com.hucong.mhl.service.DiningTableService;
import com.hucong.mhl.service.EmployeeService;
import com.hucong.mhl.service.MenuService;
import com.hucong.mhl.utils.Utility;

import java.util.List;

public class MHLView {
    public static void main(String[] args) {
        new MHLView().mainMenu();
    }
    boolean loop = true;
    private String key = "";//接受用户的选择

    //定一个employeeService对象
    private EmployeeService employeeService = new EmployeeService();

    //定义一个diningTableService对象
    private DiningTableService diningTableService = new DiningTableService();

    //定义一个MenuService对象
    private MenuService menuService = new MenuService();

    //定义一个BillService
    private BillService billService = new BillService();
    //完成点餐
    public void orderMenu(){
        System.out.println("==============点餐服务================");
        System.out.print("请选择点餐的桌号(-1退出):");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1){
            System.out.println("==============取消点餐================");
            return;
        }
        System.out.print("请选择菜品的编号(-1退出):");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1){
            System.out.println("==============取消点餐================");
            return;
        }
        System.out.print("请选择菜品的数量(-1退出):");
        int orderNums = Utility.readInt();
        if (orderNums == -1){
            System.out.println("==============取消点餐================");
            return;
        }

        //验证餐桌号是否存在
        DiningTable diningTableById = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTableById == null){
            System.out.println("==============餐桌号不存在================");
            return;
        }
        //验证菜品号是否正确
        Menu menuById = menuService.getMenuById(orderMenuId);
        if (menuById == null){
            System.out.println("==============菜号不存在================");
            return;
        }
       // System.out.print("确认是否点这个菜(y/n):");
        if (billService.orderMenu(orderMenuId,orderNums,orderDiningTableId)){
            System.out.println("==============点餐成功================");
        }else {
            System.out.println("==============点餐失败================");
        }
    }

    //显示账单信息
    public void listBill(){
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        List<Bill> list = billService.list();
        for (Bill bill : list) {
            System.out.println(bill);
        }
        System.out.println("==============显示完成================");

        //显示菜品 多表查询
//        System.out.println("\n菜名\t\t\t编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t状态");
//        List<MultiTableBean> list2 = billService.list2();
//        for (MultiTableBean bill : list2) {
//            System.out.println(bill);
//        }
//        System.out.println("==============显示完成================");
    }

    //显示所有菜品
    public void listMenu(){
        List<Menu> list = menuService.list();
        System.out.println("\n菜品编号\t\t菜品名\t\t\t类别\t\t\t价格");
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("==============显示完成================");
    }
    //完成结账
    public void payBill(){
        System.out.println("==============结账服务================");
        System.out.print("请选择要结账的餐桌编号(-1退出):");
        int payDiningTableId = Utility.readInt();
        if (payDiningTableId == -1){
            System.out.println("==============顾客取消支付================");
            return;
        }
        if (diningTableService.getDiningTableById(payDiningTableId) == null){
            System.out.println("===========未查询到该餐号==============");
            return;
        }
        if (!(billService.hasPayBillByDiningTableId(payDiningTableId))){
            System.out.println("=========未查询到需要支付的账单============");
            return;
        }
        System.out.println("确认是否要结账(y/n)");
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            System.out.println("结账的方法(现金/微信/支付宝)回车表示退出");
            String payState = Utility.readString(20,"");
            if ("".equals(payState)){
                return;
            }
            if (billService.payBill(payDiningTableId, payState)) {
                System.out.println("==============支付成功================");
            }else {
                System.out.println("==============支付失败================");
            }

        } else if (c == 'N') {
            System.out.println("==============顾客取消支付================");
            return;
        }

    }

    //完成订座
    public void orderDiningTable(){
        System.out.println("==============预定餐桌================");
        System.out.println("请选择预定餐桌的编号(-1退出)");
        int orderId = Utility.readInt();
        if (orderId == -1){
            System.out.println("============取消预定餐桌=============");
            return;
        }
        DiningTable diningTableById = diningTableService.getDiningTableById(orderId);
        if (diningTableById == null){
            System.out.println("预定的餐桌编号不存在");
            return;
        }else if (!("空".equals(diningTableById.getState()))){
            System.out.println("你选择预定的餐桌已经被预定");
            return;
        }
        System.out.println("确认是否预定(y/n)");
        while (true){
            String c = Utility.readString(1);
            if ("y".equals(c)){
                System.out.print("预订人名字:");
                String orderName = Utility.readString(50);
                System.out.print("预订人电话:");
                String orderTel = Utility.readString(20);
                diningTableService.orderDiningTable(orderId,orderName,orderTel);
                System.out.println("============预定餐桌成功=============");
                return;
            } else if ("n".equals(c)) {
                System.out.println("============取消预定餐桌=============");
                return;
            }else {
                System.out.println("请输入正确选项");
            }
        }

    }

    //显示所有餐桌状态
    public void listDiningTable(){
        List<DiningTable> list = diningTableService.list();
        System.out.println("\n餐桌编号   餐桌状态");
        for (DiningTable diningTable : list) {
            System.out.println(diningTable.getId() + "\t\t\t" + diningTable.getState());
        }
        System.out.println("================================");
    }
    public void mainMenu(){
        while (loop){
            System.out.println("===========满汉楼============");
            System.out.println("\t\t1 登陆满汉楼");
            System.out.println("\t\t2 退出满汉楼");
            System.out.print("请输入你的选择:");
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.print("你输入员工号");
                    String empId = Utility.readString(50);
                    System.out.print("你输入密 码");
                    String pwd = Utility.readString(50);
                    Employee emp = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if (emp != null){
                        System.out.println("===========登入成功["+emp.getName()+"]=============\n");
                        //显示二级菜单
                        while (loop){
                            System.out.println("=======满汉楼二级菜单==========");
                            System.out.println("\t\t1 显示餐桌状态");
                            System.out.println("\t\t2 预定餐   桌");
                            System.out.println("\t\t3 显示所有菜品");
                            System.out.println("\t\t4 点餐服   务");
                            System.out.println("\t\t5 查看账   单");
                            System.out.println("\t\t6 结      账");
                            System.out.println("\t\t9 退出 满汉楼");
                            System.out.println("请输入你的选择 :");
                             key = Utility.readString(1);
                            switch (this.key){
                                case "1":
                                 listDiningTable();
                                    break;
                                case "2":
                                   orderDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("选择错误，请重新选择");
                            }
                        }
                    } else {
                        System.out.println("===========登入失败=============");
                    }
                    break;
                case "2":
                    System.out.println("退出满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("选择只能在1和2中选择");
            }
        }

    }


}
