package com.hc.bank;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration//代替spring.xml配置文件
@ComponentScan("com.hc.bank")//组件扫描
@EnableTransactionManagement//开启事务注解
@PropertySource("jdbc.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {

    @Bean(name = "myDateSource")
    public DruidDataSource getDataSource(@Value("${jdbc.driverClass}") String driverClass,
                                         @Value("${jdbc.url}") String url,
                                         @Value("${jdbc.username}") String username,
                                         @Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

}
