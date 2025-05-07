package com.hucong.mhl.service;

import com.hucong.mhl.dao.BillDAO;
import com.hucong.mhl.dao.MultiTableDAO;
import com.hucong.mhl.domain.Bill;
import com.hucong.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

public class BillService {
    //定义一个BillDAO
    private BillDAO billDAO = new BillDAO();
    //定义一个MenuService
    private MenuService menuService = new MenuService();

    //定义一个DiningTableService
    private DiningTableService diningTableService = new DiningTableService();

    //定义一个MultiTableDAO
    private MultiTableDAO multiTableDAO = new MultiTableDAO();
    //编写点餐方法
    public boolean orderMenu(int menuId,int nums,int diningTableId){
        //生成一个账单号 UUID
        String billID = UUID.randomUUID().toString();

        //将账单生成到bill表 要求直接计算出账单金额
        int update = billDAO.Update("insert into bill values(null,?,?,?,?,?,now(),'未结帐')",
                billID, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        if (update <= 0){
            return false;
        }

        //需要更新餐桌状态
       return diningTableService.updateDiningTableState(diningTableId,"就餐中");
    }

    //返回所有的账单
    public List<Bill> list(){
        return billDAO.queryMulti("select * from bill",Bill.class);
    }
    public List<MultiTableBean> list2() {
        return multiTableDAO.queryMulti("SELECT NAME, bill.* FROM bill,menu WHERE bill.menuId = menu.id",MultiTableBean.class);

    }
        //查看 某 个 餐桌是否否有未结帐的账单
    public boolean hasPayBillByDiningTableId(int diningTableId){
        Bill bill =
                billDAO.querySingle
                        ("SELECT * FROM bill WHERE diningTableId = ? AND state = '未结帐' LIMIT 0,1", Bill.class, diningTableId);
    return bill != null;
    }

    //完成结账[如果餐桌存在 并且该餐桌有未结帐的账单]
    public boolean payBill(int diningTableId,String payMode){
        //修改bill表
        int update =
                billDAO.Update
                        ("update bill set state = ? where diningTableId = ? and state = '未结帐'", payMode, diningTableId);
        if (update <= 0){
            return false;
        }
        //修改diningTable表
        if (!diningTableService.updateDiningTableToFree(diningTableId,"空")){
            return false;
        }
        return true;
    }
}
