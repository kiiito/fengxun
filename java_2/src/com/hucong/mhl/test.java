package com.hucong.mhl;

import com.hucong.mhl.utils.JDBCUtilsByDruid;
import com.hucong.mhl.utils.Utility;

import java.sql.Connection;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        int i = Utility.readInt();
        System.out.println(i);
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
