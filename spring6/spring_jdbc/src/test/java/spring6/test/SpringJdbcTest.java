package spring6.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.hc.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpringJdbcTest {
    //回调函数
    @Test
    public void testCallback(){

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select id,name,age from t_user where id = ?";
        //注册回调函数
        User user = jdbcTemplate.execute(sql, new PreparedStatementCallback<User>() {
            @Override
            public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, 1);
                ResultSet resultSet = ps.executeQuery();
                User user = null;
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    user = new User(id, name, age);
                }
                return user;
            }
        });
        System.out.println(user);
    }

    @Test
    public void testBatchDelete(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "delete from t_user  where id = ?";
        Object[] obj1 = {5};
        Object[] obj2 = {6};
        Object[] obj3 = {7};
        Object[] obj4 = {8};
        ArrayList<Object[]> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }
    @Test
    public void testBatchUpdate(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "update t_user set name = ? where id = ?";
        Object[] obj1 = {"尤拉1",5};
        Object[] obj2 = {"尤拉2",6};
        Object[] obj3 = {"尤拉3",7};
        Object[] obj4 = {"尤拉4",8};
        ArrayList<Object[]> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }

    @Test
    public void testBatchInsert(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql ="insert into t_user(name,age)values(?,?)";
        Object[] obj1 = {"刻晴1",18};
        Object[] obj2 = {"刻晴2",19};
        Object[] obj3 = {"刻晴3",20};
        Object[] obj4 = {"刻晴4",21};
        ArrayList<Object[]> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }


    @Test
    public void testSelect(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql1 = "select * from t_user where id = ?";
        String sql2 = "select * from t_user";
        String sql3 = "select count(1) from t_user";
        User user = jdbcTemplate.queryForObject(sql1, new BeanPropertyRowMapper<>(User.class), 1);
        List<User> users = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(User.class));
        Integer total = jdbcTemplate.queryForObject(sql3, int.class);
        System.out.println(user + "  " + users + " " + total);
    }

    @Test
    public void testJdbcUpdate(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
//        System.out.println(jdbcTemplate);
        String sql1 ="insert into t_user(name,age)values(?,?)";
        String sql2 = "update t_user set name = ? where id = ?";
        String sql3 = "delete from t_user where id = ?";
        int count = jdbcTemplate.update(sql1, "王五", 20);
        count += jdbcTemplate.update(sql2,"甘雨",1);
        count += jdbcTemplate.update(sql3,3);
        System.out.println(count);
    }
}
