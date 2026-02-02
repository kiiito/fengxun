package com.hc.dao;

import com.hc.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    UserBean selectById(int id);
}
