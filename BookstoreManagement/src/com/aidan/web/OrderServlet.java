package com.aidan.web;

import com.aidan.pojo.*;
import com.aidan.service.OrderService;
import com.aidan.service.impl.OrderServiceImpl;

import com.aidan.utils.JdbcUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();
        // 调用orderService.createOrder(Cart,Userid);生成订单

        Order order = orderService.createOrder(cart, userId);

        /* 测试事务管理
        String orderId = null;

        try {
            orderId = orderService.createOrder(cart, userId);

            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }*/

        // req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);


        req.getSession().setAttribute("order", order);

        req.getSession().setAttribute("orderId", order.getOrderId());

        // 避免请求转发后刷新重复提交订单
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

}
