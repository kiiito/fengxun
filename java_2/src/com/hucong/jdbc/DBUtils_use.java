package com.hucong.jdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_use {
    @Test
    public void testQueryMany() throws SQLException {
        /**
         * 使用druids 和 DBUtils 工具类来完成对表的crud操作
         */
        // 1 连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2 使用DBUtils 类和接口 先引入jar包
        // 3 创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from actor where id >= ?";
        // 4 执行相关方法 返回ArrayList 集合集
        /**
         * 1 query 方法就是执行SQL语句 得到resultSet 封装到 ArrayList
         * 2 返回集合
         * 3 connection 连接
         * 4 SQL 执行的SQL语句
         * 5 new BeanListHandler<>(Actor.class) 在将resultSet -> Actor 对象 ->封装到ArrayList
         * 底层用反射机制 去获取Actor 类的属性 然后进行封装
         * 6 1 就是将SQL语句中的？赋值 可以有多个值
         * 7 底层在得到resultSet 后会在query关闭 也会关闭preparedStatement
         *
         * 源码解析
         *  public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
         *         if (conn == null) {
         *             throw new SQLException("Null connection");
         *         } else if (sql == null) {
         *             throw new SQLException("Null SQL statement");
         *         } else if (rsh == null) {
         *             throw new SQLException("Null ResultSetHandler");
         *         } else {
         *             Statement stmt = null; //定义PreparedStatement
         *             ResultSet resultSet = null;//接收返回的resultSet
         *             T result = null;// 返回ArrayList
         *
         *             try {
         *                 if (params != null && params.length > 0) {//判断传进来的数值是否为空或者长度是否大于0
         *                     PreparedStatement ps = this.prepareStatement(conn, sql);//创建prepareStatement
         *                     stmt = ps;
         *                     this.fillStatement(ps, params);//对SQL进行？赋值
         *                     resultSet = this.wrap(ps.executeQuery());//返回resultSet 封装到 ArrayList
         *                 } else {
         *                     stmt = conn.createStatement();
         *                     resultSet = this.wrap(((Statement)stmt).executeQuery(sql));
         *                 }
         *
         *                 result = rsh.handle(resultSet);
         *             } catch (SQLException var12) {
         *                 this.rethrow(var12, sql, params);
         *             } finally {
         *                 this.closeQuietly(resultSet);//关闭resultSet
         *                 this.closeQuietly((Statement)stmt);//关闭prepareStatement
         *             }
         *
         *             return result;
         *         }
         *     }
         */

        List<Actor> query =
                queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 1);
        for(Actor actor : query){
            System.out.println(actor);
        }
        JDBCUtilsByDruid.close(null,null,connection);
    }

    @Test
    //演示完成单个对象(单条记录)
    public void testQuerySingle() throws SQLException{
        // 1 连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2 使用DBUtils 类和接口 先引入jar包
        // 3 创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from actor where id = ?";
        //返回单行记录 单个对象 使用的Handler 是 BeanHandler
        //如果填入一个不存在的id 则会返回一个null
        Actor query = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 1);
        System.out.println(query);
        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }
    @Test
    //演示完成查询单行单列 返回的就是object对象
    public void testScalar()throws SQLException{
        // 1 连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2 使用DBUtils 类和接口 先引入jar包
        // 3 创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from actor where id = ?";
        //如果填入一个不存在的id 则会返回一个null
        Object query = queryRunner.query(connection, sql, new ScalarHandler<>(), 1);
        System.out.println(query);
        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }
    @Test
    //演示完成dml
    public void testDML()throws SQLException{
        // 1 连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2 使用DBUtils 类和接口 先引入jar包
        // 3 创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //String sql = "update actor set name = ? where id = ?";
        //String sql = "insert into actor values (?,?,?,?,?)";
        String sql = "delete from actor where id = ?";

        //int affectedRow = queryRunner.update(connection, sql, "黎明", 2);
        int affectedRow = queryRunner.update(connection, sql,  3);
        //int affectedRow = queryRunner.update(connection, sql, 3,"郭富城","男","1979-10-10","118");
        System.out.println(affectedRow>0 ? "执行成功":"执行并没有影响表");
        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }
}
