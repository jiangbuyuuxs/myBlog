<%@ page pageEncoding="UTF-8" %>
<div class="float-nav">
    <div class="list-group">
        <a class="list-group-item" href="/">首页</a>
        <a class="list-group-item" href="/cy/cyjl">成语接龙</a>
        <a class="list-group-item" href="#">****************</a>

        <a class="list-group-item" href="javascript:BlogTool.logout();" >登出</a>
        <a class="list-group-item" href="/go/logon">登录</a>
        <a class="list-group-item" href="/admin/go/admin#/info">管理员</a>
    </div>
</div>
<input type="hidden" id="csrfParam" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="row copyright">
    <p>Copyright © 2016-2017 - Powered by textBlog - Hosted by Xxxx - 商务合作 - </p>
    <p>最近访问人数{xxx},最后更新于2017年5月9日 20:33:50</p>
</div>
