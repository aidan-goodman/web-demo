<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>修改密码</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="loginassets/css/main.css"/>
    <noscript>
        <link rel="stylesheet" href="loginassets/css/noscript.css"/>
    </noscript>
</head>

<body class="landing is-preload">

<!-- Page Wrapper -->
<div id="page-wrapper">


    <!-- Three -->
    <section id="three" class="wrapper style3 special">
        <div class="inner">
            <header class="major">
                <h2>修改密码</h2>
                <p>欢迎您&bull;${user.name }&emsp;<a href="#" onclick="history.go(-1)">后退</a></p>
            </header>
            <div class="inner">
                <form action="userServlet?type=update" method="post">
                    <input type="hidden" name="id" value="${user.id }">
                    <input name="pwd" type="password" required="required" placeholder="请输入一个新的密码">
                    <br/>
                    <ul class="actions stacked">
                        <li>
                            <button type="submit" class="button fit primary">提交并登录</button>
                        </li>
                        <li>
                            <p style="color:red">${error }</p>
                        </li>
                    </ul>
                </form>
            </div>

            <%--静态包含 features 内容--%>
            <%@include file="/common/features.jsp" %>

        </div>
    </section>
</div>

<!-- Scripts -->
<script src="loginassets/js/jquery.min.js"></script>
<script src="loginassets/js/jquery.scrollex.min.js"></script>
<script src="loginassets/js/jquery.scrolly.min.js"></script>
<script src="loginassets/js/browser.min.js"></script>
<script src="loginassets/js/breakpoints.min.js"></script>
<script src="loginassets/js/util.js"></script>
<script src="loginassets/js/main.js"></script>

</body>

</html>
