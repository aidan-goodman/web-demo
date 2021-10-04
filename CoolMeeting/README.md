# Cool Meeting

一个基于 SSM 框架的会议管理系统，使用技术栈为：SSM + FreeMarker + jQuery + Ajax + MySQL

项目开发依赖统一使用 Maven 管理：

1. spring-webmvc：SpringMVC 的依赖，其中包含 Spring
2. javax.servlet-api：为了兼容性没有使用 jakarta 包
3. spring-jdbc
4. mysql-connector-java
5. druid：可能存在无法兼容 JDK11 的情况，[解决方法](https://aidanblog.top/2021/08/25/Solution-Maven-druidApply11/)
6. mybatis：
7. mybatis-spring
8. aspectjweaver
9. aspectjrt
10. spring-context-support
11. freemarker

## 搭建流程

1. 首先进行 SSM 的整合与配置，创建配置文件，如：Spring 的 applicationContext.xml、SpringMVC 的 spring-servlet.xml、数据库连接配置文件 db.properties。对 web.xml 进行最基础的配置，自动扫描 applicationContext.xml 信息、servlet 的配置、UTF-8 编码过滤器解决中文乱码
2. 完善配置文件，建立基础的包结构
3. 整合 FreeMarker，在 spring-servlet.xml 中进行相应配置
4. 更改静态网页为 .ftl 的模板网页，抽取公共区域（头部导航，菜单区域），将用户信息存放到 Session 中，方便对不同权限用户显示各自的页面

## 模块细节

系统面向用户和管理员共同使用，二者通过 Interceptor 进行简单的权限隔离，对抽取的公共菜单，面对不同权限用户时显示不同的选项

* 用户注册和登录：简单的数据库查询、插入，使用 Model 实现数据回填；管理员可以对注册的用户进行审批以及搜索全部用户
* 部门管理
* 会议室管理

## 总结

本项目使用最简单的方式实现一些常见功能，旨在锻炼对 SSM 的配置和实际应用