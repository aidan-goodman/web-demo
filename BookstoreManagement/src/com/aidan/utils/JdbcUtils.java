package com.aidan.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {

        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties 配置文件
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = conns.get();

        if (conn == null) {
            try {
                //从数据库连接池中获取连接
                conn = dataSource.getConnection();

                // 保存到ThreadLocal对象中，供后面的jdbc操作使用
                conns.set(conn);

                // 设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }


/*     // 关闭连接，放回数据库连接池

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        // 如果不等于null，说明 之前使用过连接，操作过数据库
        if (connection != null) {
            try {
                connection.commit(); // 提交 事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

}