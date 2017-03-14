<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach items="${hotwords}" var="hotwords">
<p>${hotwords.key} -- ${hotwords.value}</p>
</c:forEach>
</body>
</html>
