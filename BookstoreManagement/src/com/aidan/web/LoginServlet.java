package com.aidan.web;

import com.aidan.pojo.User;
import com.aidan.service.UserService;
import com.aidan.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 调用业务层UserService的Login()方法处理请求
        User loginUser = userService.login(new User(null, username, password, null));

        // 3. 根据Login()返回值判断用户名密码是否匹配
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req, resp);

        }
    }
}
