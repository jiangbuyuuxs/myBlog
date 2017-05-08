<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>textBlog</title>
    <%@include file="../../comm/jscss.jsp" %>
    <script>
        $(function () {
            var createPagebar = function (blogNums) {
                var pageSize = 10;//TODO 从后端取
                var curPage = 1;
                var pageNum = 0;
                if (blogNums < pageSize) {
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
                    var page = parseInt($(this).attr("data-page"));
                    _changePage(page);
                }

                function _changePage(page) {
                    if (page < 1 || page > pageNum || page === curPage) {
                        return;
                    }
//                    $("a[aria-label=Previous]").removeClass("hidden");
//                    $("a[aria-label=Next]").removeClass("hidden");
                    var order = 0;//TODO 获取排序条件
                    //ajax请求
                    $.ajax({
                        url: "/blog/" + page + "/page/" + order + "/order",
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
//                            if (curPage == 1) {
//                                $("a[aria-label=Previous]").addClass("hidden");
//                            }
//                            if (curPage == pageNum) {
//                                $("a[aria-label=Next]").addClass("hidden");
//                            }
                        }
                    })
                }
            };

            function recreateBlogList(data) {
                $(".blog-list").empty();
                for (var i = 0, len = data.length; i < len; i++) {
                    $('<div class="row">' +
                    '<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title">[<a href="/blog/' + data[i].id + '/del">删除</a>]<a target="_blank" title="' + data[i].title + '" href="/admin/blog/' + data[i].id + '/edit">' + data[i].title + '</a></div>' +
                    '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">' + data[i].cdate + '</div>' +
                    '</div>').appendTo($(".blog-list"));
                }
            }

            createPagebar(${blogNums});
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <a class="btn btn-success" href="/admin/blog/go/add" target="_blank">写博文</a>
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
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 title">
                            [<a href="/blog/${blog.id}/del">删除</a>]
                            <a title="${blog.title}" target="_blank"
                               href="/admin/blog/${blog.id}/edit">${blog.title}</a>
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
    </div>
</div>
</body>
</html>
