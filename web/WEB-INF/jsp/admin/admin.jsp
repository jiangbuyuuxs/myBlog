<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/1
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台</title>
    <%@include file="../comm/jscss.jsp" %>
    <style>
        .adminwin {
            border: 1px solid #dddddd;
            border-radius: 4px;
        }
    </style>
    <script type="text/x-template" id="user-manager-template">
        <table class="table table-condensed">
            <tr>
                <th>操作</th>
                <th>用户名</th>
                <th>昵称</th>
                <th>激活状态</th>
            </tr>
            <div class="loading" v-if="loading">
                Loading...
            </div>

            <tr v-for="user of users">
                <td><a class="btn btn-default btn-xs" href="#" @click.prevent="deleteUser(user.username)">删除</a><a class="btn btn-default btn-xs" href="#" @click.prevent="editUser(user.username)">编辑</a></td>
                <td>{{user.username}}</td>
                <td>{{user.nickname}}</td>
                <td :class="user.enabled===1?'text-danger':'text-success'">{{user.enabled===1?'激活':'未激活'}}</td>
            </tr>
        </table>
    </script>
    <script>
        $(function () {
            Vue.component("userManager", {
                template:"#user-manager-template",
                data:function(){
                    return {
                        users:[],
                        curPage:1,
                        loading:false
                    };
                },
                methods:{
                    fetchData: function (page) {
                        this.loading = true;
                        var url = "/admin/user/"+page+"/page";
                        this.$http.get(url).then(function(data){
                            this.loading = false;
                            this.users = data.data;
                            this.curPage = page;
                        },function(response){
                            this.loading = false;
                            console.log(data.msg);
                        });
                    },
                    deleteUser: function (username) {
                        var sure = confirm("是否删除该用户");
                        if(sure){
                            var url = "/admin/user/"+username+"/del/"+this.curPage+"/page";
                            this.$http.get(url).then(function(data){
                                this.users = data.data;
                            },function(response){
                                console.log(data.msg);
                            });
                        }
                    }
                },
                created () {
                    this.fetchData(1);
                }
            });
            var userManager = Vue.component('userManager');
            var blogManager = {template:'<div>博文管理</div>'};

            var routes = [
                    { path: '/user/manager', component: userManager },
                    { path: '/blog/manager', component: blogManager }
            ];
            var router = new VueRouter({routes:routes});
            var adminManager = new Vue({
                        el: "#admin-manager",
                        data: {
                            userinfo: {}
                        },
                        router
                    }
            );

        });
    </script>
</head>
<body class="container">
<div id="admin-manager">
    <div class="row">
        <div>这是头部,一般用来干点什么呢</div>
    </div>
    <div class="row">
        <div class="col-sm-2">
            <ul class="list-group">
                <li class="list-group-item"><router-link to="/user/manager">用户管理</router-link></li>
                <li class="list-group-item"><router-link to="/blog/manager">博文管理</router-link></li>
            </ul>
        </div>
        <div class="col-sm-10 adminwin">
            <router-view></router-view>
        </div>
    </div>
</div>
</body>
</html>
