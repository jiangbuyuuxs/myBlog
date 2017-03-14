<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title></title>
    <%@include file="comm/head.jsp" %>
    <style>
        .title {
            padding: 2px;
        }

        .index-head-img {
            line-height: 200px;
            margin: 10px -15px;
            border: 1px solid #aaaaaa;
            background-image: url("/resources/img/index.jpg");
            transition: -ms-transform 0.2s ease-out;
        }

        .index-head-img:hover {
            transform: rotateY(180deg);
        }

        .head-img-container {
            width: 125px;
            height: 125px;
            margin: 0 auto;
            background: url("/resources/img/head.jpg") center no-repeat;;
        }

        .blog-list {
            min-height: 400px;
        }

        .blog-list .row {
            margin: 10px 0px;
            padding: 2px;
            border-radius: 3px;
        }

        .blog-list-head {
            margin: 5px 0 0 0;
        }

        .left-panel {
            border: solid 1px #cecece;
        }

        .right-panel {
            border: solid 1px #cecece;
        }

        .line1 {
            background: #dff0d8;
        }

        .line2 {
            background: #d9edf7;
        }

        .line3 {
            background: #fcf8e3;
        }

        .line4 {
            background: #c5f1f3;
        }

        .user-name {
            text-align: center;
        }

        .sp20 {
            height: 20px;
        }

        .blog-tag {
            padding: 5px;
        }

        .blog-tag a {
            font: 12px "微软雅黑";
            border-radius: 5px;
            border: solid 1px #cecece;
            text-decoration: none;
            color: #c2c2c2;
            word-break: keep-all;
            padding: 1px;
            margin: 0 0 3px 0;
            display: inline-block;
        }

        .blog-tag a:hover {
            color: #dcdcdc;
        }
        .remark{
            margin:0 10px;
        }
        .fav-title{
            margin: 0 -10px;
            background: #d9edf7;
        }
    </style>
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
            createPagebar(${blogNums});
            setTitleBg();
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
            <div class="row sp20 fav-title">
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
        </div>
    </div>
</div>
</body>
</html>
