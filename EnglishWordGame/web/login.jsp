<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="rememberUser.jsp" %>
<html>

<head>
    <title>英语单词游戏</title>
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

    <!-- Header -->
    <header id="header" class="alt">
        <h1><a href="index.html">英语单词游戏</a></h1>
        <nav id="nav">
            <ul>
                <li class="special">
                    <a href="#menu" class="menuToggle"><span>菜单</span></a>
                    <div id="menu">
                        <ul>
                            <li><a href="#banner">首页</a></li>
                            <li><a href="#one">游戏介绍</a></li>
                            <li><a href="#three">登录</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
    </header>

    <!-- Banner -->
    <section id="banner">
        <div class="inner">
            <h2>英语单词游戏</h2>
            <p>由 Aidan 制作<br/>
            <ul class="actions special">
                <li><a href="#three" class="button primary">登录</a></li>
            </ul>
        </div>
        <a href="#one" class="more scrolly">下拉显示更多</a>
    </section>

    <!-- One -->
    <section id="one" class="wrapper style1 special">
        <div class="inner">
            <header class="major">
                <h2>初步了解英语单词游戏</h2>
                <p>英语单词游戏是一个单词统计的练习游戏。英文单词随机显示，<br/>
                    用户在 5 秒内选择单词对应的中文词语，提交答案。<br/>
                    通过计分、页面的特效等提高趣味性，<br/>
                    使学习和记单词成为一种乐趣。</p>
            </header>
            <ul class="icons major">
                <li><span class="icon fa-diamond major style1"><span class="label">Lorem</span></span></li>
                <li><span class="icon fa-heart-o major style2"><span class="label">Ipsum</span></span></li>
                <li><span class="icon fa-code major style3"><span class="label">Dolor</span></span></li>
            </ul>
        </div>
    </section>

    <!-- Two -->
    <section id="two" class="wrapper alt style2">
        <section class="spotlight">
            <div class="image"><img src="images/pic01.jpg" alt=""/></div>
            <div class="content">
                <h2>积分<br/>
                    排名</h2>
                <p>游戏可以记录用户的积分，并与其他用户进行排名比较。</p>
            </div>
        </section>
        <section class="spotlight">
            <div class="image"><img src="images/pic02.jpg" alt=""/></div>
            <div class="content">
                <h2>单词<br/>
                    难度分级</h2>
                <p>单词分为简单、中等、困难三种，用户可以根据自己的水平选择，也可以选择随机难度。</p>
            </div>
        </section>
    </section>

    <!-- Three -->
    <section id="three" class="wrapper style3 special">
        <div class="inner">
            <header class="major">
                <h2>登录账号</h2>
                <p>登录账号即可开始享受以下服务</p>
            </header>

            <%--静态包含 features 内容--%>
            <%@include file="/common/features.jsp" %>

            <div class="inner">
                <form action="loginServlet" method="post">
                    <input name="name" type="text" required="required" placeholder="请输入账号" value="<%=name %>">
                    <br/>
                    <input name="pwd" type="password" required="required" placeholder="请输入密码" value="<%=pwd %>">
                    <br/>
                    <input id="remember-me" type="checkbox" name="rememberUser" checked="checked">
                    <label for="remember-me">
                        记住密码
                    </label>
                    <ul class="actions stacked">
                        <li>
                            <p style="color:red">${error }</p>
                        </li>
                        <li>
                            <button type="submit" class="button fit primary">登录</button>
                        </li>
                        <li>
                            <button type="button" onclick="window.location.href='sign-up.jsp'" class="button fit">注册
                            </button>
                        </li>
                    </ul>
                </form>
            </div>
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