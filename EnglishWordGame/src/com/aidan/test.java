package com.aidan;

import com.aidan.util.DBUtil;

public class test {
    public static void main(String[] args) throws Exception {
        if (DBUtil.getConn() != null) {
            System.out.println("θΏζ₯ζε");
        }
    }
}
