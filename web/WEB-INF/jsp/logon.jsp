<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/5
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <%@include file="comm/jscss.jsp" %>
    <link rel="stylesheet" href="/resources/css/logon.css">
    <script>
        $(function () {
           var logonUsername = "<sec:authentication property="name"/>";
            if(logonUsername!="anonymousUser"){
                $(".logined-username").text(logonUsername);
                $(".logon-panel").hide();
                $(".logined-panel").show();
            }else{
                $(".logined-panel").hide();
            }

            $(".logout").one("click",function(){
                $.ajax("/logout",{
                    data:{isAjax:true},
                    success: function (data) {
                        $(".logon-panel").show();
                        $(".logined-panel").hide();
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-2 left-panel">

        </div>
        <div class="col-lg-6 logined-panel">
            <p>当前已登录,登录用户为<span class="logined-username"></span></p>
            <p>是否<a class="btn btn-danger btn-xs logout" href="#">退出</a>,或者<a class="btn btn-success btn-xs" href="javascript:history.go(-1);">返回</a></p>
        </div>
        <div class="col-lg-6 logon-panel">
            <form class="form-horizontal" action="/login" method="post">
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">用户名</label>

                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="username" id="username" value="admin"
                               logoned="<sec:authentication property="name"/>" placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">密码</label>

                    <div class="col-sm-7">
                        <input type="password" class="form-control" name="password" id="password" value="admin" placeholder="咒语">
                        <a class="forget-pw" href="/go/forget">忘记密码</a>
                    </div>
                </div>
                <div class="form-group hidden">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="text" class="form-control" name="returnUrl" id="returnUrl"
                               value="${sessionScope.get("returnUrl")}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-2">
                        <button type="submit" class="btn btn-success">登 录</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-2">
                        <p>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-2 right-panel">
            <div class="other-logon-list">
                <button class="btn btn-success">其他方式登录</button>
            </div>
        </div>
    </div>
<%@include file="comm/footer.jsp" %>
</div>
</body>
</html>
