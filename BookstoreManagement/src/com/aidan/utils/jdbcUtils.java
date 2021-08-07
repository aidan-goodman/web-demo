package com.aidan.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {

    private static DruidDataSource dataSource;

    static {

        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties 配置文件
            InputStream resourceAsStream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(resourceAsStream);
            // 创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            // 测试连接是否成功
            // System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接处中的连接
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // 获取连接对象
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭连接，放回数据库连接池
     */
    public static void close(Connection conn) {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
