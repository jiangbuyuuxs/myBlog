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
  <script type="text/javascript" charset="utf-8" src="/resources/ueditor/ueditor.all.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
  <%@include file="../comm/head.jsp" %>
  <script>
    $(function(){
      var ue = UE.getEditor('editor');
      $(".send").bind("click", function () {
        var content = ue.getContent();
        if(content!==""){
          var titleVal = $(".title").val();
          $.ajax("/admin/addblogs",{
            type:"POST",
            dataType:"json",
            data:{
              title:titleVal,
              texts:content
            },
            success:function(data){
              console.log(data);
              if(data.success){
                alert("写了一篇~~~~~");
                  window.location.href="http://localhost:8080";
              }
            }
          });
        }
      });
    });
  </script>
</head>
<body>
<%@include file="../comm/loginstatebar.jsp" %>
<div class="container">
  <div class="form-group">
    <label for="classtype">分类</label>
    <input type="text" id="classtype" class="title form-control" name="type" placeholder="类型" value="${blogs.classtype}">
  </div>
  <div class="form-group">
    <label for="title">标题</label>
    <input type="text" id="title" class="title form-control" name="title" placeholder="标题哦" value="${blogs.title}">
  </div>
  <div>
    <script id="editor" type="text/plain" style="width:800px;height:400px;"></script>
  </div>
  <input type="button" class="send button btn-success" value="发布" />
</div>


</body>
</html>
