package com.aidan.test;

import com.aidan.pojo.User;
import com.aidan.service.UserService;
import com.aidan.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void testRegistUser() {
        userService.registUser(new User(null, "Julie", "123456", "Julie@qq.com"));
    }

    @Test
    public void testLogin() {
        System.out.println(userService.login(new User(null, "aidan", "123456", null)));
    }

    @Test
    public void testExistsUsername() {
        if (userService.existsUsername("wzg16888")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}