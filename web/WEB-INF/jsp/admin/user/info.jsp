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
    <title>用户信息</title>
    <%@include file="../../comm/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-3">用户名</div>
        <div class="col-sm-3">${user.username}</div>
    </div>
    <div class="row">
        <div class="col-sm-3">昵称</div>
        <div class="col-sm-3">${user.nickname}</div>
    </div>
    <div class="row">
        <div class="col-sm-3">邮箱</div>
        <div class="col-sm-3">${user.email}</div>
    </div>

</div>
</body>
</html>
