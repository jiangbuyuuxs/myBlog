<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <a class="navbar-brand" href="/user/userinfo"><sec:authentication property="name"/></a>
        <ul class="nav navbar-nav">
            <li class="right-pill"><a href="/logout">退出</a></li>
        </ul>
    </div>
</nav>
