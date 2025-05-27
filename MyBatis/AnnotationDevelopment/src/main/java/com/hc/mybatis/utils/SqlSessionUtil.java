package com.hc.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * mybatis工具类
 */
public class SqlSessionUtil {
    //防止new对象
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sqlSessionFactory;

    //SqlSessionFactory对象一个数据库对应一个 所以调用时只需创建一个就行
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //全局的 服务器级别的 一个服务器当中定义一个即可 ThreadLocal
    //为了保证一个线程对应一个SqlSession
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
            //将sqlSession对象绑定到当前对象当中
            local.set(sqlSession);
        }
        return sqlSession;
    }
    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            //需要移除与当前线程的绑定关系 防止下一次再次使用该线程
            local.remove();
        }
    }
}
