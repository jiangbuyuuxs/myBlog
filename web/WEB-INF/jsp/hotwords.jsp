<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach items="${hotwords}" var="hotword">
<p>${hotword.remark} -- ${hotword.num}</p>
</c:forEach>
</body>
</html>
