<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title></title>
    <%@include file="comm/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="/resources/css/index.css">
    <script>
        $(function () {
            var createPagebar = function (blogNums) {
                var pageSize = 10;//TODO 从后端取
                var curPage = 1;
                var pageNum = 0;
                if (blogNums <= pageSize) {
                    $(".page-bar").hide();
                    return;
                }
                pageNum = Math.ceil(blogNums / pageSize), i = 1;
                for (; i <= pageNum; i++) {
                    $("<li><a href='#' data-page='" + i + "'>" + i + "</a></li>").insertBefore($(".pagination li:last"));
                }
                $(".pagination li:eq(1)").addClass("active");
                $("a[data-page]").on("click", changePage);
                $("a[aria-label=Previous]").on("click", prePage);
                $("a[aria-label=Next]").on("click", nextPage);

                function prePage() {
                    _changePage(curPage - 1);
                }

                function nextPage() {
                    _changePage(curPage + 1);
                }

                function changePage() {
                    var page = $(this).attr("data-page");
                    _changePage(page);
                }

                function _changePage(page) {
                    if (page < 1 || page > pageNum || page === curPage)
                        return;
                    var order = 0;//TODO 获取排序条件
                    //ajax请求
                    $.ajax({
                        url: "blog/" + page + "/page/" + order + "/order",
                        dataType: "json",
                        success: function (data) {
                            recreateBlogList(data.data);
                            curPage = page;
                        },
                        error: function (data) {
                            console.log(data.msg);
                        },
                        complete: function () {
                            $(".pagination li.active").removeClass("active");
                            $("a[data-page=" + curPage + "]").parent().addClass("active");
                        }
                    })
                }
            };

            function recreateBlogList(data) {
                $(".blog-list").empty();
                for (var i = 0, len = data.length; i < len; i++) {
                    $('<div class="row">' +
                    '<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title"><a title="' + data[i].title + '" href="/detail/' + data[i].id + '/id">' + data[i].title + '</a></div>' +
                    '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">' + data[i].cdate + '</div>' +
                    '</div>').appendTo($(".blog-list"));
                }
                setTitleBg();
            }

            function setTitleBg() {
                $(".blog-list .row").addClass(function () {
                    return "line" + (Math.floor((Math.random() * 4)) + 1);
                });
            }

            createPagebar(${blogNums});
            setTitleBg();
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row index-head-img">
        &nbsp;
    </div>
    <div class="hidden">
        <a class="btn btn-default" href="/admin/add">写一篇</a>
        <a class="btn btn-default" href="/admin/admin">后台</a>
        <a class="btn btn-default" href="/go/about">关于</a>
    </div>
    <div class="row">
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
                                                                                         pattern="yyyy-MM-dd HH:mm:ss"/></div>
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
        <div class="col-lg-2 col-md-2 hidden-xs hidden-sm col-sm-0 col-xs-0 right-panel">
            <div class="row sp20"></div>
            <div class="row">
                <div class="head-img-container">
                </div>
            </div>
            <div class="row">
                <div class="user-name">
                    Mrz
                </div>
            </div>
            <div class="row sp20"></div>
            <div class="row">
                <div class="remark">
                    滴滴答答的敲代码
                </div>
            </div>
            <div class="row sp20"></div>
            <div class="row sp20 fav-word-title">
                最喜欢用的词囧囧囧
            </div>
            <div class="row">
                <div class="blog-tag">
                    <a href="/detail/1/id">爱爱爱</a>
                    <a href="/detail/1/id">搜索神马</a>
                    <a href="/detail/1/id">到底谁是X</a>
                    <a href="/detail/1/id">方法论是说的啥</a>
                    <a href="/detail/1/id">FFF团烧火</a>
                    <a href="/detail/1/id">爱A爱A爱</a>
                    <a href="/detail/1/id">搜DD索</a>
                    <a href="/detail/1/id">aa到底</a>
                    <a href="/detail/1/id">asd方法</a>
                    <a href="/detail/1/id">FFF</a>
                </div>
            </div>
            <div class="row sp20"></div>
            <div class="row sp20 hot-blog-title">
                热门博文
            </div>
            <div class="row">
                <div class="top-blog">
                    <div class="top-blog-list">
                        <c:forEach items="${hotBlogs}" var="hotBlog">
                            <p><a href="/detail/${hotBlog.id}/id">${hotBlog.title}</a></p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
