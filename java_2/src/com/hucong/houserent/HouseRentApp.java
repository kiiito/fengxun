package com.hucong.houserent;

import com.hucong.houserent.view.HouseView;

public class HouseRentApp {
    public static void main(String[] args) {
//        HouseView houseView = new HouseView();
//        houseView.mainMenu();
        new HouseView().mainMenu();
        System.out.println("====你退出了房屋系统====");
    }
}
