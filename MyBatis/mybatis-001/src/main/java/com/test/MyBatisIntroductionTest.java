package com.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisIntroductionTest {
    public static void main(String[] args) throws IOException {
        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //Resources.getResourceAsStream默认从类的根目录下查找资源
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactory对象  一般情况下都是一个数据库对应一个SqlSessionFactory对象
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        //获取SqlSession对象
        SqlSession sqlSession = build.openSession();

        //执行SQL语句 存放SQL语句的id
        int insertCar = sqlSession.insert("insertCar");//返回影响数据表的记录条数
        System.out.println("插入了" + insertCar + "条记录");

        //需要手动提交才行
        sqlSession.commit();
    }
}
