# myBlog
### 动动手,免得不记得怎么写hello world了
* 框架 Spring Hibernate,Spring有的就用上了,MVC,Security等.版本信息见pom.xml
* 数据库 mysql5.7 驱动版本mysql-connector-java-5.1.40
* 在线编辑器 百度编辑器ueditor1.4.3.3

###环境要求
* JDK1.8+

### 部署(面向开发者)
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

* 管理员页面 /admin/go/admin
  个人信息 /admin/user/{username}/info  
  个人信息管理 /admin/user/go/manager  
  添加用户 /admin/user/go/add  
  博文添加 /admin/blog/go/add  
  博文管理 /admin/blog/go/manager  
  博文删除 /admin/blog/{id}/del  
  博文编辑 /admin/blog/{id}/edit  