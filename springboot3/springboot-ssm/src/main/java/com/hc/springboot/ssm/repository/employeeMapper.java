package com.hc.springboot.ssm.repository;

import com.hc.springboot.ssm.bean.employee;
import com.hc.springboot.ssm.bean.product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface employeeMapper {
    @Select("select * from spring6.emplyoee  ")
    List<employee> selectAll();
    @Insert("insert into ssm_demo1.employee ( name, age, position) values (#{name},#{age},#{position})")
    void addData(employee employee);

    @Delete("delete from ssm_demo1.employee where id=#{id}")
    void deleteData(Integer id);

    @Update("update ssm_demo1.employee set name=#{employee.name},age=#{employee.age},position=#{employee.position} where id=#{id}")
    void updateData(Integer id, employee employee);


}
