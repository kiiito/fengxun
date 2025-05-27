package com.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisCompleteTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            //开启会话 底层就会开启事务
            sqlSession = sqlSessionFactory.openSession();
            //执行SQL语句处理相关业务
            int count = sqlSession.insert("insertCar");//返回影响数据表的记录条数
            System.out.println("插入了" + count + "条记录");
            //没有发生异常 提交事务 终止事务
            sqlSession.commit();
        } catch (Exception e) {
            //最好回滚事务
            //sqlSession不为空的情况下回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
