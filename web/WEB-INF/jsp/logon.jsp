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
    <style>
        body{
            background: #76c7ce;
        }
        .container {
            margin-top: 50px;
        }
        .logon-panel{
            background: rgba(255, 117, 93, 0.70);
            border-radius: 5px;
            padding-top:20px;
            min-height: 240px;
        }
        .other-place{
            border-radius: 5px;
        }
        .other-logon-list{
            background: rgba(206, 60, 25, 0.2);
            border-radius: 5px;
            min-height: 240px;
            text-align: center;
            padding:10px 0;
        }
        .forget-pw,.forget-pw:hover{
            color:#000;
            font-size: 12px;
            text-decoration: none;
            padding: 10px 0;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-2 left-panel">
            <div class="other-place list-group">
                    <span href="#" class="list-group-item active">其他去处</span>
                    <a class="list-group-item" href="/">首页</a>
                    <a class="list-group-item" href="/cy/cyjl">成语接龙</a>
            </div>
        </div>
        <div class="col-lg-6 logon-panel">
            <form class="form-horizontal" action="/login" method="post">
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">用户名</label>

                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="username" id="username"
                               logoned="<sec:authentication property="name"/>" placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">密码</label>

                    <div class="col-sm-7">
                        <input type="password" class="form-control" name="password" id="password" placeholder="咒语">
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

</div>
</body>
</html>
