package com.aidan.test;

import com.aidan.utils.jdbcUtils;
import org.testng.annotations.Test;

public class jdbcUtilsTest {
    @Test
    public void testJdbcUtils() {
        System.out.println(jdbcUtils.getConnection());
    }
}
