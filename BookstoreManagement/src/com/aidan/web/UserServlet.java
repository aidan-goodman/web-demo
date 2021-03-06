package com.aidan.web;

import com.aidan.pojo.User;
import com.aidan.service.UserService;
import com.aidan.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {

    private final UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数username
        String username = req.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }



    /**
     * 登录功能
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 调用业务层UserService的Login()方法处理请求
        User loginUser = userService.login(new User(null, username, password, null));

        // 3. 根据Login()返回值判断用户名密码是否匹配
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户或密码错误！");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            // 保存用户登录的信息到Session域中
            req.getSession().setAttribute("user", loginUser);
            //跳到成功页面login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

        }
    }

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        // if ("abcde".equalsIgnoreCase(code)) {
        if (token != null && token.equalsIgnoreCase(code)) {
            // 3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                // 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));

                // 跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 注销ion
     *
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
    }

}
