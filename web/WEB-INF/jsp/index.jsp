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

        .indexhead {
            line-height: 200px;
            margin: 10px 0;
            border: 1px solid #aaaaaa;
        }

        .indexheadimg {
            background-image: url("/resources/img/index.jpg");
            transition: -ms-transform 0.2s ease-out;
        }

        .indexheadimg:hover {
            transform: rotateY(180deg);
        }

        /*.indexheadimg2 {*/
        /*background-image: url("/resources/img/index2.jpg")*/
        /*}*/

        .blog-list {
            min-height: 240px;
        }
    </style>
    <script>
        $(function () {
//            var indexheadimg2 = new Image();
//            indexheadimg2.src = "/resources/img/index2.jpg";
//            indexheadimg2.onload = function () {
//                $(".indexhead").hover(function () {
//                    $(this).toggleClass("indexheadimg2");
//                });
//            };

            var createPagebar = function (blogNums) {
                var pageSize = 10;//TODO 从后端取
                var curPage = 1;
                var pageNum = 0;
                if (blogNums <= pageSize) {
                    $(".pagebar").hide();
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
            function recreateBlogList(data) {
                $(".blog-list").empty();
                for (var i = 0, len = data.length; i < len; i++) {
                    $('<div class="row">' +
                    '<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title"><a title="' + data[i].title + '" href="/detail/' + data[i].id + '/id">' + data[i].title + '</a></div>' +
                    '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">' + data[i].cdate + '</div>' +
                    '</div>').appendTo($(".blog-list"));
                }
            }
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
            <div class="blog-list">
                <c:forEach items="${blogs}" var="blog">
                    <div class="row">
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title"><a title="${blog.title}"
                                                                                  href="/detail/${blog.id}/id">${blog.title}</a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><fmt:formatDate value="${blog.cdate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                    </div>
                </c:forEach>
            </div>
            <div class="pagebar">
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
        <div class="col-lg-2 col-md-2 hidden-xs hidden-sm col-sm-0 col-xs-0">
            这是右边栏
        </div>
    </div>
</div>
</body>
</html>
