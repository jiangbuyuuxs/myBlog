<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/5
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>入入入</title>
    <%@include file="comm/head.jsp" %>
    <style>
        .logon-panel{margin-top:50px;}
    </style>
</head>
<body>
<div class="container logon-panel">
    <form class="form-horizontal" action="/login" method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>

            <div class="col-sm-2">
                <input type="text" class="form-control" name="username" id="username" value="<sec:authentication property="name"/>" placeholder="用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>

            <div class="col-sm-2">
                <input type="password" class="form-control" name="password" id="password" placeholder="咒语">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <p>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
            </div>
        </div>
        <div class="form-group hidden">
            <div class="col-sm-offset-2 col-sm-2">
                <input type="text" class="form-control" name="returnUrl" id="returnUrl" value="${sessionScope.get("returnUrl")}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button type="submit" class="btn btn-default">登录</button>
                <button type="reset" class="btn btn-default">取消</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
