<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="jakarta.servlet.http.Cookie" %>

<%//读取Cookie %>
<%
    String name = "";
    String pwd = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        //遍历Cookie
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if ("name".equals(cookie.getName())) {
                name = URLDecoder.decode(cookie.getValue(), "utf-8");
            } else if ("pwd".equals(cookie.getName())) {
                pwd = URLDecoder.decode(cookie.getValue(), "utf-8");
            }
        }
    } else {
        //查看Cookie是否存在
        //System.out.println("没有Cookie");
    }
%>
