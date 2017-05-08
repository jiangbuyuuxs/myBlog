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
    <title>${oper}</title>
    <script type="text/javascript" charset="utf-8" src="/resources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/resources/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
    <%@include file="../../comm/jscss.jsp" %>
    <script>
        $(function () {
            var ue = UE.getEditor('editor');
            ue.ready(function () {
                ue.setContent($("#blogtexts").html(), true);
            });
            function saveBlog() {
                $(this).addClass("disable");
                $(this).unbind("click");
                var content = ue.getContent();
                if (content !== "") {
                    var blogid = $(".blogid").val(),
                            titleVal = $(".title").val();
                    if (isNaN(parseInt(blogid))) {
                        $.ajax("/admin/blog/add", {
                            type: "POST",
                            dataType: "json",
                            data: {
                                title: titleVal,
                                texts: content
                            },
                            success: function (data) {
                                console.log(data);
                                if (data.success) {
                                    alert("写了一篇~~~~~");
                                    window.location.href = "http://localhost:8080";
                                }
                            }
                        });
                    } else {
                        $.ajax("/admin/blog/edit", {
                            type: "POST",
                            dataType: "json",
                            data: {
                                id: blogid,
                                title: titleVal,
                                texts: content
                            },
                            success: function (data) {
                                console.log(data);
                                if (data.success) {
                                    alert("修改了一篇~~~~~");
                                    window.location.href = "http://localhost:8080/detail/" + blogid + "/id";
                                }
                            },
                            error: function (XMLHttpRequest, textStatus) {
                                alert(textStatus);
                                $(".send").bind("click", saveBlog);
                            }
                        });
                    }
                }
            }

            $(".send").bind("click", saveBlog);
        });
    </script>
</head>
<body>
<div class="container">
    <input type="hidden" id="blogid" class="blogid form-control" name="blogid" placeholder="编号"
           value="${blog.id}">

    <div class="form-group">
        <label for="classtype">分类</label>
        <input type="text" id="classtype" class="classtype form-control" name="classtype" placeholder="类型"
               value="${blog.classtype}">
    </div>
    <div class="form-group">
        <label for="title">标题</label>
        <input type="text" id="title" class="title form-control" name="title" placeholder="标题哦" value="${blog.title}">
    </div>
    <div>
        <script id="editor" type="text/plain" style="width:800px;height:400px;"></script>
    </div>
    <input type="button" class="send button btn-success" value="发布"/>
    <script id="blogtexts" type="text/html">${blog.texts}</script>
</div>


</body>
</html>
