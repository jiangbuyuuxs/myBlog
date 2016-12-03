<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title></title>
    <%@include file="comm/head.jsp" %>
    <style>
        .title{padding:2px;}
        .indexhead{line-height:200px;margin:10px 0;border:1px solid #aaaaaa;}
        .indexheadimg{background-image:url("/resources/img/index.jpg")}
        .indexheadimg2{background-image:url("/resources/img/index2.jpg")}
    </style>
    <script>
        $(function(){
            var indexheadimg2 = new Image();
            indexheadimg2.src = "/resources/img/index2.jpg";
            indexheadimg2.onload = function(){
               $(".indexhead").hover(function () {
                   $(this).toggleClass("indexheadimg2");
               });
            };
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row indexhead indexheadimg">
        &nbsp;
    </div>
    <div class="hidden">
        <a class="btn btn-default" href="/admin/add">写一篇</a>
        <a class="btn btn-default" href="/admin/admin">后台</a>
        <a class="btn btn-default" href="/about">关于</a>
    </div>
    <div class="row">
        <div class="col-lg-10 col-md-9 col-sm-12 col-xs-12">
            <div>
                <c:forEach items="${blogs}" var="blog">
                    <div class="row">
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title"><a title="${blog.title}" href="/detail/${blog.id}/id">${blog.title}</a></div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">${blog.cdate}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-lg-2 col-md-2 hidden-xs hidden-sm col-sm-0 col-xs-0">
            这是右边栏
        </div>
    </div>
</div>
</body>
</html>
