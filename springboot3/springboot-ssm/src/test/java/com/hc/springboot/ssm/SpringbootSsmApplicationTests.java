package com.hc.springboot.ssm;

import com.hc.springboot.ssm.bean.employee;
import com.hc.springboot.ssm.bean.product;
import com.hc.springboot.ssm.repository.StudentsMapper;
import com.hc.springboot.ssm.repository.employeeMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootSsmApplicationTests {

    @Resource
    private employeeMapper empMapper;
    @Resource
    private StudentsMapper studentsMapper;

    @Test
    void contextLoads() {
        List<employee> emp = empMapper.selectAll();
        System.out.println(emp);

    }

    @Test
    void selectAll() {
        List<employee> emp = empMapper.selectAll();
        System.out.println(emp);
    }

    @Test
    void addData() {
        employee employee = new employee("黄六", 22, "老板");
        empMapper.addData(employee);
    }

    @Test
    void deleteData() {
        Integer id = 5;
        empMapper.deleteData(id);
    }

    @Test
    void updateData() {
        Integer id = 2;
        employee employee = new employee("胡八", 28, "经理");
        empMapper.updateData(id, employee);
    }


    @Test
    public void select(){
        String color="白色家电";
        List<product> list= studentsMapper.findBytupename(color);
        System.out.println(list);
    }



}
