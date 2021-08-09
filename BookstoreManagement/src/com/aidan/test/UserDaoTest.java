package com.aidan.test;

import com.aidan.dao.UserDao;
import com.aidan.dao.impl.UserDaoImpl;
import com.aidan.pojo.User;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void testQueryUserByUsername() {

        //System.out.println(userDao.queryUserByUsername("aidan"));

        if (userDao.queryUserByUsername("aidan") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void testQueryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("aidan", "123456") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void testSaveUser() {
        System.out.println(userDao.saveUser(new User(null, "jack", "123456", "jack@qq.com")));
    }
}