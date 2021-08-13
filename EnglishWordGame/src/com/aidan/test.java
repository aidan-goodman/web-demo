package com.aidan;

import com.aidan.util.DBUtil;

public class test {
    public static void main(String[] args) throws Exception {
        if (DBUtil.getConn() != null) {
            System.out.println("连接成功");
        }
    }
}
