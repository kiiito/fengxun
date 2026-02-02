package com.hc.springboot.ssm.repository;

import com.hc.springboot.ssm.bean.Students;
import com.hc.springboot.ssm.bean.product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentsMapper {


    Students selectstu(String name, String smajor);

    Students selectByname(String name);

    Students selectBysmajor(String name, String smajor);

    Students selectnotnull(String name, String smajor);

    List<Students> selectall(int id);


    List<product> findBytupename(String color);
}
