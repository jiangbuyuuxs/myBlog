<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/1
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>博文显示</title>
  <%@include file="comm/head.jsp" %>
</head>
<body>
<p><a href="/">首页</a></p>
<h2>${blog.title}</h2>
<p>${blog.cdate}</p>
<p>${blog.texts}</p>

</body>
</html>
