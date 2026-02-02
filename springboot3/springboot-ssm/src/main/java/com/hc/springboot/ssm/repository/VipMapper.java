package com.hc.springboot.ssm.repository;

import com.hc.springboot.ssm.bean.Vip;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vip record);

    int insertSelective(Vip record);

    Vip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);
    Vip findByName(String name);
}