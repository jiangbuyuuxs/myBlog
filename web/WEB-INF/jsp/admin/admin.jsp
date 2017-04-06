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
    <title>管理博客</title>
  <%@include file="../comm/head.jsp" %>
  <style>
    .adminwin{
      border:1px solid #dddddd;
      border-radius: 4px;
    }
  </style>
</head>
<body>
<%@include file="../comm/loginstatebar.jsp" %>
<div class="row">
  <div class="col-sm-2">
    <ul class="list-group">
      <li class="list-group-item"><a href="/admin/user/go/manager" target="adminwin">用户管理</a></li>
      <!--<li class="list-group"><a href="/admin/message/go/manager" target="adminwin">消息管理</a></li>-->
      <li class="list-group-item"><a href="/admin/blog/go/manager" target="adminwin">博文管理</a></li>
    </ul>
  </div>
  <div class="col-sm-10 adminwin">
    <iframe src="/admin/go/systeminfo" frameborder="0" name="adminwin" width="100%" height="400px"></iframe>
  </div>
</div>
</body>
</html>
