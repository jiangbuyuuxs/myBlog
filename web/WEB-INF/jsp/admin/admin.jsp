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

        .blog-list {
            height: 430px;
        }

        .loading {
            text-align: center;
            position: absolute;
            top: 300px;
            left: 300px;
        }
        .add-blog {
            font-size: 12px;
        }
    </style>
    <script type="text/x-template" id="blog-info-template">
        <table class="table table-striped">
            <tbody>
            <tr>
                <td>博文总数</td>
                <td>{{info.blogsCount}}</td>
                <td>总访问人数</td>
                <td>{{info.visitCount}}</td>
            </tr>
            <tr>
                <td>xxxx</td>
                <td>{{info.blogsCount}}</td>
                <td>yyyy</td>
                <td>{{info.visitCount}}</td>
            </tr>
            </tbody>
        </table>

    </script>
    <script type="text/x-template" id="user-manager-template">
        <table class="table table-striped">
            <tbody>
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
                <td><a class="btn btn-default btn-xs" href="#" @click.prevent="deleteUser(user.username)">删除</a><a
                        class="btn btn-default btn-xs" href="#" @click.prevent="editUser(user.username)">编辑</a></td>
                <td>{{user.username}}</td>
                <td>{{user.nickname}}</td>
                <td :class="user.enabled===1?'text-danger':'text-success'">{{user.enabled===1?'激活':'未激活'}}</td>
            </tr>
            </tbody>
        </table>
    </script>
    <script type="text/x-template" id="blog-manager-template">
        <div>
            <div class="loading" v-if="loading">
                Loading...
            </div>
            <div class="blog-list">
                <table class="table table-striped">
                    <tbody>
                    <tr>
                        <th width="10%">操作</th>
                        <th width="70%">标题</th>
                        <th width="20%">最后修改时间</th>
                    </tr>
                    <tr v-for="blog of blogs">
                        <td><a class="btn btn-default btn-xs" href="#" @click.prevent="deleteBlog(blog.id)">删除</a><a
                                class="btn btn-default btn-xs" :href="'/admin/blog/'+blog.id+'/edit'" target="_blank">编辑</a></td>
                        <td><a :href="'/detail/'+blog.id+'/id'" target="_blank">{{blog.title}}</a></td>
                        <td>{{blog.edate}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="page-bar" :class="{hidden:pageNum<2}">
                <ul class="pagination">
                    <li><a href="#" @click.prevent="fetchData(curPage-1)"><span>&laquo;</span></a></li>
                    <li v-for="i in pageNum" :class="{active:i===curPage}"><a href="#" @click.prevent="fetchData(i)"
                                                                              :data-page="i">{{i}}</a></li>
                    <li><a href="#" @click.prevent="fetchData(curPage+1)"><span>&raquo;</span></a></li>
                </ul>
            </div>
        </div>
    </script>
    <script>
        $(function () {

            Vue.component("infoPanel", {
                template: "#blog-info-template",
                data: function () {
                    return {
                        info: {},
                        loading: false
                    };
                },
                methods: {
                    fetchData: function (page) {
                        this.loading = true;
                        var url = "/admin/blogInfo";
                        this.$http.get(url).then(function (data) {
                            BlogTool.checkLogin(data);
                            this.info = data.data;
                            this.loading = false;
                        }, function (response) {
                            this.loading = false;
                            console.log(data.msg);
                        });
                    }
                },
                created ()
            {
                this.fetchData();
            }
        });

        Vue.component("userManagerPanel", {
            template: "#user-manager-template",
            data: function () {
                return {
                    users: [],
                    curPage: 1,
                    loading: false
                };
            },
            methods: {
                fetchData: function (page) {
                    this.loading = true;
                    var url = "/admin/user/" + page + "/page";
                    this.$http.get(url).then(function (data) {
                        BlogTool.checkLogin(data);
                        this.loading = false;
                        this.users = data.data;
                        this.curPage = page;
                    }, function (response) {
                        this.loading = false;
                        console.log(data.msg);
                    });
                },
                deleteUser: function (username) {
                    var sure = confirm("是否删除该用户(" + username + ")");
                    if (sure) {
                        var url = "/admin/user/" + username + "/del/" + this.curPage + "/page";
                        this.$http.get(url).then(function (data) {
                            this.users = data.data;
                        }, function (response) {
                            console.log(data.msg);
                        });
                    }
                }
            },
            created ()
        {
            this.fetchData(1);
        }
        })
        ;
        Vue.component("blogManagerPanel", {
            template: "#blog-manager-template",
            data: function () {
                return {
                    blogs: [],
                    blogNums: 0,
                    pageNum: 0,
                    curPage: 0,
                    loading: false
                };
            },
            methods: {
                fetchData: function (page, init) {
                    if (!init)
                        if (page < 1 || page > this.pageNum || page === this.curPage)
                            return;
                    this.loading = true;
                    var url = "/admin/blog/" + page + "/page";
                    this.$http.get(url).then(function (data) {
                        BlogTool.checkLogin(data);
                        this.loading = false;
                        this.blogs = data.data.blogs;
                        this.blogNums = data.data.blogNums;
                        this.pageNum = Math.floor((this.blogNums / 10) + 1);
                        this.curPage = page;
                    }, function (response) {
                        this.loading = false;
                        console.log(data.msg);
                    });
                },
                deleteBlog: function (id) {
                    var sure = confirm("是否删除该博文(id为:" + id + ")");
                    if (sure) {
                        var url = "/admin/blog/" + id + "/del/" + this.curPage + "/page";
                        this.$http.get(url).then(function (data) {
                            this.blogs = data.data;
                        }, function (response) {
                            console.log(data.msg);
                        });
                    }
                }
            },
            created ()
        {
            this.fetchData(1, true);
        }
        })
        ;
        var infoPanel = Vue.component('infoPanel');
        var userManager = Vue.component('userManagerPanel');
        var blogManager = Vue.component('blogManagerPanel');

        var routes = [
            {path: '/info', component: infoPanel},
            {path: '/', component: infoPanel},
            {path: '/user/manager', component: userManager},
            {path: '/blog/manager', component: blogManager}
        ];
        var router = new VueRouter({routes: routes});
        var adminManager = new Vue({
                    el: "#admin-manager",
                    data: {
                        userinfo: {}
                    },
                    router
                }
        );

        })
        ;
    </script>
</head>
<body class="container">
<div id="admin-manager">
    <div class="row">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <span class="navbar-brand">哦,管理员</span>
                </div>
                <div class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">操作 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">掘金</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="col-sm-2">
            <ul class="list-group">
                <li class="list-group-item">
                    <router-link to="/info">博客信息管理</router-link>
                </li>
                <li class="list-group-item">
                    <router-link to="/user/manager">用户管理</router-link>
                </li>
                <li class="list-group-item">
                    <router-link to="/blog/manager">博文管理</router-link>
                    <span class="add-blog">[<a href="/admin/blog/go/add" target="_blank">写一篇</a>]</span>
                </li>
            </ul>
        </div>
        <div class="col-sm-10 adminwin">
            <router-view></router-view>
        </div>
    </div>
</div>
</body>
</html>
