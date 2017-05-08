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
  <%@include file="comm/jscss.jsp" %>
  <style>
    .highlight {
      padding: 9px 14px;
      margin-bottom: 14px;
      background-color: #f7f7f9;
      border: 1px solid #e1e1e8;
      border-radius: 4px;
    }
  </style>
</head>
<body class="container">
<div>
  <a class="btn btn-default" href="/">首页</a>
</div>
<h2>${blog.title}<small>${blog.cdate}</small></h2>
<div class="highlight">${blog.texts}</div>
<script type="text/javascript" charset="utf-8" src="/visitblog/${blog.id}"></script>
</body>
</html>
