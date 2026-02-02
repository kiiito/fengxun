package com.hc.mybatis.mapper;

import com.hc.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();
    int insert(User user);
    int delete(Integer id);
    User selectById(Integer id);
    int update(User user);
}
