package com.hc.dao;

import com.hc.bean.Account;

public interface AccountDao {
    /**
     * 根据用户名查询整个对象
     * @param acton
     * @return
     */
    Account selectByAction(String acton);

    /**
     * 更新对象的属性
     * @param acton
     * @return
     */
    int update(Account acton);

}
