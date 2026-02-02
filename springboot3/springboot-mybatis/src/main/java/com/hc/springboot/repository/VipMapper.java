package com.hc.springboot.repository;

import com.hc.springboot.bean.Vip;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vip record);

    int insertSelective(Vip record);

    Vip selectByPrimaryKey(Long id);
    List<Vip> selectAll();

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);
}