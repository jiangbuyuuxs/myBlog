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
    <title>添加用户</title>
    <%@include file="../../comm/head.jsp" %>
</head>
<body>
<div class="container logon-panel">
    <form class="form-horizontal" action="/admin/user/add" method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" name="username" id="username" >
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" name="password" id="password">
            </div>
        </div>
        <div class="form-group">
            <label for="nickname" class="col-sm-2 control-label">昵称</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" name="nickname" id="nickname">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" name="email" id="email">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button type="submit" class="btn btn-default">添加</button>
                <button type="reset" class="btn btn-default">取消</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
