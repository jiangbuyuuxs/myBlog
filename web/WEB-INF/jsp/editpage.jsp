<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/1
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>xx博文</title>
  <script type="text/javascript" charset="utf-8" src="resources/ueditor/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="resources/ueditor/ueditor.all.js"> </script>
  <script type="text/javascript" charset="utf-8" src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
<p>${oper}</p>

<div>
  <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<script>
  UE.getEditor('editor');
</script>
</body>
</html>
