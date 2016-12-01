<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
这是首页!!!!
<ol>
    <li><a href="add">写一篇</a></li>
    <li><a href="admin">后台</a></li>
    <li><a href="about">关于</a></li>
</ol>

<ul>
<c:forEach items="${blogs}" var="blog">
    <li><a href="detail/${blog.id}/id">${blog.title}</a>---${blog.cdate}</li>
</c:forEach>
</ul>
</body>
</html>
