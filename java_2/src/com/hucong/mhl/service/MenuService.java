package com.hucong.mhl.service;

import com.hucong.mhl.dao.MenuDAO;
import com.hucong.mhl.domain.Menu;

import java.util.List;

public class MenuService {

    private MenuDAO menuDAO = new MenuDAO();

    //返回所有菜品
    public List<Menu> list(){
        return menuDAO.queryMulti("select * from menu",Menu.class);
    }

    //需要方法 根据id返回menu对象
    public Menu getMenuById(int id){
        return menuDAO.querySingle("select * from menu where id = ?",Menu.class,id);
    }
}
