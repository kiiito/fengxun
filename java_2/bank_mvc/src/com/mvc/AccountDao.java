package com.mvc;

import com.bean.Account;
import com.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类只要负责数据的增删改查功能
 */

public class AccountDao {

    public int insert(Account act){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn =JDBCUtilsByDruid.getConnection();
            String sql = "insert into users(actno,balance)values (?,?)";
            ps =conn.prepareStatement(sql);
            ps.setString(1, act.getActno());
            ps.setDouble(2,act.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,ps,null);
        }
        return count;
    }

    public int deleteById(String id){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn =JDBCUtilsByDruid.getConnection();
            String sql = "delete from users  where id = ?";
            ps =conn.prepareStatement(sql);
            ps.setString(1,id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,ps,null);
        }
        return count;
    }

    public int update(Account act){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn =JDBCUtilsByDruid.getConnection();
            System.out.println(conn);
            String sql = "update users set balance = ?,actno = ? where id = ?";
            ps =conn.prepareStatement(sql);
            ps.setDouble(1,act.getBalance());
            ps.setString(2, act.getActno());
            ps.setLong(3,act.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,ps,null);
        }
        return count;
    }

    public Account selectByAct(String acton){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            conn =JDBCUtilsByDruid.getConnection();
            System.out.println(conn);
            String sql = "select id,actno,balance from users where actno = ?";
            ps =conn.prepareStatement(sql);
            ps.setString(1,acton);
            rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                double balance = rs.getDouble("balance");
                account = new Account(id,acton,balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(rs,ps,null);
        }
        return account;
    }

    public List<Account> selectAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> list = new ArrayList<>();
        try {
            conn =JDBCUtilsByDruid.getConnection();
            String sql = "select id,balance,actno from users";
            ps =conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double balance = rs.getDouble("balance");
                String actno = rs.getString("actno");
                Account account = new Account(id,actno,balance);
                list.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(rs,ps,null);
        }
        return list;
    }
}
