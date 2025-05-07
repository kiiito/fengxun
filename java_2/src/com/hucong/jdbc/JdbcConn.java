package com.hucong.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static java.lang.Class.forName;

public class JdbcConn {
    @Test
    public void connect01() throws SQLException {
        //1 注册驱动
        Driver driver = new Driver();//创建driver对象
        //2 得到连接
        String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
        //将用户名和密码放入properties 对象
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","hc");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    @Test
    public void connect02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //利用反射加载driver类 动态加载 更加灵活 减少依赖
        Class<?> aClass = forName("com.mysql.jdbc.Driver");
        Driver driver =(Driver) aClass.newInstance();
        //2 得到连接
        String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
        //将用户名和密码放入properties 对象
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","hc");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }
    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class<?> aClass = forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "hc";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        forName("com.mysql.jdbc.Driver");//可以省略不写,在驱动5.1.6以上的已经加载了，但最好写一下
        /*
               static {
                    try {
                        java.sql.DriverManager.registerDriver(new Driver());//已经加载完成
                    } catch (SQLException E) {
                        throw new RuntimeException("Can't register driver!");
                    }
    }
         */
        String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "hc";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    @Test
    public void connect05() throws ClassNotFoundException, IOException, SQLException {
      //  forName("com.mysql.jdbc.Driver");
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//com/hucong/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);//建议写上
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

        String sql = "update actor set name='刘德华' where id=1";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql);
        System.out.println(i>0?"成功":"失败");
    }


}
