package com.hucong.jdbc;

import com.mysql.jdbc.Statement;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Batch_ {
    @Test
    public void NotBatch() throws Exception {
        String sql = "insert into admin2 values (null,?,?)";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1,"jack" + i);
            preparedStatement.setString(2,"1111" );
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行所需时间" + (end - start));
        JDBCUtils.close((ResultSet) null, (Statement) preparedStatement,connection);
    }
    @Test
    /**
     * 进行批处理时必须在URL当中加入rewriteBatchedStatements=true
     */
    public void Batch() throws SQLException {
        String sql = "insert into admin2 values (null,?,?)";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1,"jack" + i);
            preparedStatement.setString(2,"111" );
            //将SQL语句加入批处理包中
            /*
            //1 第一就创建 ArrayList - elementData => Object[]
            //2 elementData => Object[] 就会存放我们预处理的SQL语句
            //3 当elementData满后,就会按照1.5进行扩容
            //4 当执行到指定的值 就executeBatch
            //5 批处理会减少我们发送SQL语句的网络开销 而且减少编译次数 提高效率
                public void addBatch() throws SQLException {
                        synchronized (checkClosed().getConnectionMutex()) {
                            if (this.batchedArgs == null) {
                                this.batchedArgs = new ArrayList<Object>();
                            }

                            for (int i = 0; i < this.parameterValues.length; i++) {
                                checkAllParametersSet(this.parameterValues[i], this.parameterStreams[i], i);
                            }

                            this.batchedArgs.add(new BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull));
                        }
    }
             */

            preparedStatement.addBatch();
            //当有1000条记录时 在批量执行
            if ((i + 1) % 1000 == 0){
                preparedStatement.executeBatch();
                //每执行一次 清空一把
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("执行所需时间" + (end - start));
        JDBCUtils.close((ResultSet) null, (Statement) preparedStatement,connection);
    }
}
