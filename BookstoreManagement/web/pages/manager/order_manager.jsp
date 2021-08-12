<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>

</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>
        </tr>

        <c:if test="${empty sessionScope.order}">
            <%--如果订单空的情况--%>
            <tr>
                <td colspan="4"><a href="index.jsp">亲，当前没有订单！等待客户购买吧！！！</a></td>
            </tr>
        </c:if>

        <c:if test="${not empty sessionScope.order}">
            <%--如果订单非空的情况--%>
            <tr>
                <td>${sessionScope.order.createTime.year}-${sessionScope.order.createTime.month}
                    -${sessionScope.order.createTime.day}</td>
                <td>${sessionScope.order.price}</td>
                <td><a href="#">查看详情</a></td>
                <c:if test="${sessionScope.order.status==0}">
                    <td><a href="#">点击发货</a></td>
                </c:if>
                <c:if test="${sessionScope.order.status==1}">
                    <td><a href="#">等待签收</a></td>
                </c:if>
                <c:if test="${sessionScope.order.status==2}">
                    <td><a href="#">签收完毕</a></td>
                </c:if>
            </tr>
        </c:if>

    </table>
</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>