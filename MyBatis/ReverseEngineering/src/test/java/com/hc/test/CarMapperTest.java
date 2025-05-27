package com.hc.test;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.mybatis.mapper.CarMapper;
import com.hc.mybatis.pojo.Car;
import com.hc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testDeleteByExample() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteByPrimaryKey(15L);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        //在执行DQL语句之前 开启分页功能
        int pageNum = 2;
        int pageSize = 3;
        PageHelper.startPage(pageNum,pageSize);
        List<Car> cars = mapper.selectByExample(null);
//        cars.forEach(car -> {
//            System.out.println(car);
//        });
        //封装分页信息对象 PageInfo
        PageInfo<Car> carPageInfo = new PageInfo<>(cars, 3);
        System.out.println(carPageInfo);

        sqlSession.close();
    }
}
