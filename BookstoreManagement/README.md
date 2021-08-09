# 书城管理系统

## 创建环境

IDEA + JDK + Tomcat + MySQL + JDBC

### jar 包

jar 包统一在 [Maven Repository](https://mvnrepository.com/) 进行下载，**更推荐使用 Maven**

* mysql-connector-java-8.0.25
* druid-1.2.6
* [单元测试支持包]

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

### 二次更新

![三层架构](web/static/img/Three-tier%20architecture.jpg)

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




















