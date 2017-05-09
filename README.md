# myBlog
* 一*级别的代码

### 总体架构说明
* 框架 Spring,Hibernate,Spring MVC,Spring Security.版本信息见pom.xml
* 数据库 mysql5.7 驱动版本mysql-connector-java-5.1.40
* 在线编辑器 百度编辑器ueditor1.4.3.3
* 前端 Vue2.0(vue插件,vue-resource,vue-router) BootStrap3.3 jQuery2.1.4

### 环境要求
* JDK1.8+
* 浏览器版本IE9+

### 部署
* 下载zip包,解压缩
* 用idea导入工程
* 导入数据库(表结构位于database/blog.sql),初始化用户表,用户名admin,密码admin

### 页面说明
* 前台页面  
  首页 /  
  登录页面 /go/logon  
  登出url /logout  
  博文详情 /detail/{id}/id  
  热词详情 /hotword/{id}/id

* 后台  
  管理员页面 /admin/go/admin  
  编辑博文 /admin/blog/{id}/edit  
  新增博文 /admin/blog/add    