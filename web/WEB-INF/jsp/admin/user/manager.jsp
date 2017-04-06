<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/1
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@include file="../../comm/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="btn-group">
        <a href="/admin/user/go/manager" class="btn btn-default">所有用户</a>
        <a href="/admin/user/go/add" class="btn btn-default">添加用户</a>
        <a href="#" class="btn btn-default">急</a>
    </div>
    <div class="row">
        <div class="col-sm-3">用户名</div>
        <div class="col-sm-3">昵称</div>
        <div class="col-sm-3">邮箱</div>
    </div>
    <div class="row">
        <div class="col-sm-3">xxxxx</div>
        <div class="col-sm-3">yyyy</div>
        <div class="col-sm-3">dddd@qq.com</div>
    </div>
</div>
</body>
</html>
