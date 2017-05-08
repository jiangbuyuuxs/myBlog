<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>热词搜索页</title>
    <%@include file="comm/jscss.jsp" %>
</head>
<body class="container">
<div>
    <a class="btn btn-default" href="/">首页</a>
</div>
<h3>热词相关文章</h3>
<div class="col-lg-10 col-md-9 col-sm-12 col-xs-12 left-panel">
    <div class="row blog-list-head">
        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title">标题</div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">发布时间</div>
    </div>
    <div class="blog-list">
        <c:forEach items="${blogs}" var="blog">
            <div class="row">
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title"><a title="${blog.title}"
                                                                          href="/detail/${blog.id}/id">${blog.title}</a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><fmt:formatDate value="${blog.cdate}"
                                                                                 pattern="yyyy-MM-dd HH:mm:ss"/>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="page-bar">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
