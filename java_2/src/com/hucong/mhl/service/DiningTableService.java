package com.hucong.mhl.service;

import com.hucong.mhl.dao.DiningTableDAO;
import com.hucong.mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService {
    //定义一个DiningTableDAO对象
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌的信息
    public List<DiningTable> list(){
        List<DiningTable> diningTables =
                diningTableDAO.queryMulti("select id,state from diningTable", DiningTable.class);
        return diningTables;
    }

    //根据id 查询对应的餐桌 diningTable 对象 如果返回null 表示id编号对应的餐桌不存在
    public DiningTable getDiningTableById(int id){
       return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }

    //如果餐桌可以预定 调用方法对其状态进行更新
    public boolean orderDiningTable(int id , String orderName,String orderTel){
        int update =
                diningTableDAO.Update
                        ("update diningTable set state = '已预订' ,orderName = ? ,orderTel = ? where id = ?", orderName, orderTel, id);
        return update > 0;
    }

    //需要提供一个更新餐桌状态的方法
    public boolean updateDiningTableState(int id,String state){
        int update = diningTableDAO.Update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }

    //提供方法 将指定的餐桌设置为空闲方法
    public boolean updateDiningTableToFree(int id,String state){
        int update = diningTableDAO.Update("update diningTable set state = ?,orderName ='',orderTel = '' where id = ?", state, id);
        return update > 0;
    }
}
