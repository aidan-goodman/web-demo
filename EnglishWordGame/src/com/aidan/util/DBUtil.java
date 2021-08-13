package com.aidan.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    static {
        //1.加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception {
        //2.得到一个连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordGame?serverTimezone=Asia/Shanghai", "root", "123456");
        return conn;
    }
}
