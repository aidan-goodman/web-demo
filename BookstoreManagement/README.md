# 书城管理系统

## 创建环境

IDEA + JDK + Tomcat + MySQL + JDBC

### jar 包

jar 包统一在 [Maven Repository](https://mvnrepository.com/) 进行下载，**更推荐使用 Maven**

* mysql-connector-java-8.0.25
* druid-1.2.6
* [单元测试支持包]
* servlet-api
* commons-dbutils-1.7
* commons-beanutils-1.9.4
* commons-logging-1.2（使用 BeanUtils 时必须导入）

## 运用技术

H5+C3+JS+JQuery、Servlet、Cookie&Session、Filter、XML&JSON、Ajax

## 项目介绍

**！没有应用到框架**

使用基础 JavaWeb 技术实现的一个简单但功能完善的书城管理系统，算是对技术的一个融合，用来入门我感觉还是不错

### 界面功能

......

------

## 更新记录

### 首次更新

1. 创建考虑到的所有静态页面，作为后续技术添加的一个基础承载，目前使用到的技术为 H5+C3+JS+JQuery
2. 根据模拟一个用户的使用过程进行编写，首先对注册页面进行一个表单验证
    1. 对用户名和密码进行格式（字母、数字和下划线组成）与长度（5-12位）的限制，具体使用正则表达式 `/^\w{5,12}$/`
    2. 对邮箱进行格式的验证，格式为 `xxxxx@xxx.com`，正则表达式为 `/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/`
    3. 因为只是静态页面，这里的验证码判断有内容输入即可
3. 登陆成功跳转到 regist_success.html 页面

### 第二次更新

![三层架构](web/static/img/Architecture.jpg)

1. 完善项目结构：根据三层架构对将要编写的业务代码进行解耦，方便后期维护和扩展升级
    * **web 层**
    * **service.impl 层**
    * **dao.impl 层**
    * **pojo 层**
    * test 测试包
    * utils 工具类
2. 创建数据库和所需要的表
   ```sql
      drop database if exists book;
      
      create database book;
      
      use book;
      
      create table t_user(
          `id` int primary key auto_increment,
          `username` varchar(20) not null unique,
          `password` varchar(32) not null,
          `email` varchar(200)
      );
      
      insert into t_user(`username`,`password`,`email`) values('aidan','aidan','aidan@aidan.com');
      
      select * from t_user;
   ```
3. 编写对应数据库对象的 JavaBean 进行交互
4. 编写 Dao 持久层，为了简化操作统一管理，先编写 JDBCUtils 工具类
    1. 使用阿里的数据库连接池，导入 druid.jar
    2. 将 JDBC 数据写入 jdbc.properties 方便统一修改
    3. 创建 BaseDao 父类对通用的 CRUD 方法进行封装
    4. 完成 UserDao 的实现，用户模块持久层编写完成
5. 使用 UserService 完成登录注册以及检查用户名是否存在
6. 完成 LoginServlet 和 RegistServlet

### 第三次更新

主要是将 html 文件替换为 JSP 文件和优化代码文件，都 MVC 思想了，还在 JSP 里写 Java 确实有点不纯粹

如果 IDEA 对 JSP 没有代码提示或者报错的情况，在依赖中导入 Tomcat Libraries 就好了（一个现在用不到的新技能）

* 将所有公共 html 代码进行整合到 JSP 文件中，使用 `<%@ include file="/pages/common/head.jsp" %>` 来导入，减少冗余
* 将项目路径改成动态获取的形式 `request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()`
* 使用设置 request 属性的方式实现错误信息的回显
* 合并 LoginServlet 和 RegistServlet 为 UserServlet
    1. 为每个功能请求页面（Login、Regist）的表单中创建一个隐藏的输入项：`<input type="hidden" name="action" value="login"/>`，用来做方法判断
    2. `String action = req.getParameter("action");`来获取方法，使用反射来调用（if...else不够优雅）
* 编写了 BeanUtils 方便将参数注入对象（应对参数过多的情况）

<img src="web/static/img/Information_echo.jpg" align="left" width="450" height="220"  >
<img src="web/static/img/ServletReflect.jpg" align="right" width="450" height="220"  >















