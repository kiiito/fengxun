package com.hucong.mhl.service;

import com.hucong.mhl.dao.EmployeeDAO;
import com.hucong.mhl.domain.Employee;

/**
 * 该类完成对employee表的各种操作 通过调用employeeDAO对象完成
 */
public class EmployeeService {

    //定义一个employeeDAO属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //根据empId 和 pwd返回一个employee对象
    public Employee getEmployeeByIdAndPwd(String empId,String pwd){
        Employee employee =
                employeeDAO.querySingle
                        ("select * from employee where empId = ? and pwd = md5(?)", Employee.class, empId, pwd);
        //如果没有查询到则会返回null
        return employee;
    }
}
