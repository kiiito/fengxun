package com.junit;

import com.bean.Car;
import com.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMapperTest {

    @Test
    public void testNamespace(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        /**
         * 如果有重名id就必须要引用namespace 写法 namespace.id
         */
        List<Object> cars = sqlSession.selectList("userMapper.selectAll");
        cars.forEach(car->System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Object> cars = sqlSession.selectList("selectAll");
        cars.forEach(car->System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Object selectById = sqlSession.selectOne("selectById", 1);
        System.out.println(selectById);
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(13L, "1005", "比亚迪", 12.0, "2020-10-09", "新能源");
        int count = sqlSession.update("updateCarByBean", car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDel() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
//        int count = sqlSession.delete("deleteByCarNum", 1111);
        int count = sqlSession.delete("deleteByCarNum", 1111);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByBean() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(null, "122", "小米su7", 21.99, "2024-10-10", "新能源");
        int count = sqlSession.insert("insertCar", car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar() {

        SqlSession sqlSession = SqlSessionUtil.openSession();
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "1111");
        map.put("k2", "比亚迪");
        map.put("k3", 10.0);
        map.put("k4", "2020-11-11");
        map.put("k5", "燃油车");
        int count = sqlSession.insert("insertCar", map);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByUtil() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.insert("insertCar");
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testEncodingStream() throws Exception {
        // 使用字节流精确控制
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "UTF-8");

        String original = "测试中文";
        ps.println(original);
        System.out.write(baos.toByteArray()); // 绕过编码层直接输出

        // 数据库验证
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mvc?useSSL=false&characterEncoding=UTF-8",
                "root", "hc")) {

            // 使用PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO t_car(brand) VALUES(?)");
            pstmt.setString(1, original);
            pstmt.executeUpdate();

            // 二进制读取验证
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT HEX(brand) FROM t_car ORDER BY id DESC LIMIT 1");
            if (rs.next()) {
                System.out.println("数据库存储的HEX值: " + rs.getString(1));
            }
        }
    }
}

