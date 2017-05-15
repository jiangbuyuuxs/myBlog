<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>textBlog</title>
    <%@include file="comm/jscss.jsp" %>
    <link rel="stylesheet" type="text/css" href="/resources/css/index.css">
    <script>
        $(function () {

            var isMobile = checkMobile();

            function checkMobile (){
                return $('.head-bar-open-btn').css('display')=='block';
            }

            $(window).on('scroll', function () {
                changeHeadBarPostionByScroll();
            });

            $(window).on('resize', function () {
                isMobile = checkMobile();
                changeHeadBarPosition();
                changeHeadBarPostionByScroll();
            });
            changeHeadBarPosition();

            function changeHeadBarPostionByScroll(){
                if($('body').scrollTop()>0&&!isMobile){
                    $('.head-panel').addClass('pc');
                }else if(!isMobile){
                    $('.head-panel').removeClass('pc');
                }
            }

            function changeHeadBarPosition(){
                if(checkMobile()){
                    //移动模式
                    $('.head-panel').addClass('mobile');
                }else{
                    $('.head-panel').removeClass('mobile');
                }
            }


            var pageSize = 10;//TODO 从后端取
            var pageNum = ${blogNums};
            pageNum = Math.ceil(pageNum / pageSize);

            var blogPanel = new Vue({
                el: '.main-panel',
                data: {
                    blogs:${blogs},
                    pageNum: pageNum,
                    curPage: 1,
                    hotBlogs:${hotBlogs},
                    hotWords:${hotWords}
                },

                methods: {
                    prePage: function () {
                        this._changePage(this.curPage - 1);
                    },

                    nextPage: function () {
                        this._changePage(this.curPage + 1);
                    },

                    changePage: function (page) {
                        this._changePage(page);
                    },
                    _changePage: function (page) {
                        if (page < 1 || page > pageNum || page === this.curPage)
                            return;
                        var order = 0;//TODO 获取排序条件
                        var url = "blog/" + page + "/page/" + order + "/order";
                        this.$http.get(url).then(function(data){
                            this.blogs = data.data.data;
                            this.curPage = page;
                        },function(data){
                            console.log(data.msg);
                        });
                    }
                }
            });
        });
    </script>
</head>
<body class="container">
<div class="head-panel">
    <div class="navbar navbar-default" id="head-bar-container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed pull-left head-bar-open-btn glyphicon glyphicon-th-large" data-toggle="collapse" data-target=".head-bar-collapse"></button>
        </div>
        <div class="collapse navbar-collapse head-bar-collapse">
            <nav class="navbar navbar-default head-bar">
                <ul class="nav navbar-nav">
                    <li><a href="/">首页</a></li>
                    <li><a href="/cy/cyjl">成语接龙</a></li>
                    <li><a href="/blog/list">博客列表</a></li>
                    <li><a href="/go/logon">登录</a></li>
                    <li><a href="/admin/go/admin#/info">管理员</a></li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="hide-header">
        <!--占位用的.防止滚动抖动-->&nbsp;
    </div>
</div>
<div class="main-panel">
    <div class="row index-head-img">
        &nbsp;
    </div>
    <div class="row">
        <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12 blog-panel">
            <div class="row blog-list-head">
                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-8 title">标题</div>
                <div class="col-lg-2 col-md-3 col-sm-3 col-xs-4">发布时间</div>
            </div>
            <div class="blog-list">
                <div v-for="(blog,index) in blogs" class="row">
                    <div class="col-lg-10 col-md-9 col-sm-9 col-xs-8 padding2px"><a :title="blog.title"
                                                                                   :href="'/detail/'+blog.id+'/id'">{{blog.title}}</a>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-3 col-xs-4 padding2px">{{blog.cdate}}</div>
                </div>
            </div>
            <div class="page-bar">
                <ul class="pagination">
                    <li><a href="#" @click="prePage"><span>&laquo;</span></a></li>
                    <li v-for="i in pageNum" :class="{active:i===curPage}"><a href="#" @click="changePage(i)"
                                                                              :data-page="i">{{i}}</a></li>
                    <li><a href="#" @click="nextPage"><span>&raquo;</span></a></li>
                </ul>
            </div>
        </div>
        <div class="col-lg-3 col-md-4 hidden-xs hidden-sm right-panel">
            <div class="right-container">
            <div class="row sp25"></div>
            <div class="row hidden">
                <div class="head-img-container">
                </div>
            </div>
            <div class="row">
                <div class="user-name">
                    Mrz
                </div>
            </div>
            <div class="row sp25"></div>
            <div class="row">
                <div class="remark">
                    滴滴答答的敲代码
                </div>
            </div>
            <div class="row sp25"></div>
            <div class="row sp25 column-title">
                最喜欢用的词
            </div>
            <div class="row">
                <div class="blog-tag">
                    <a v-for="hotword in hotWords" :href="'/hotword/'+hotword.hashcode+'/id'">{{hotword.remark}}</a>
                </div>
            </div>
            <div class="row sp25"></div>
            <div class="row sp25 column-title">
                热门博文
            </div>
            <div class="row">
                <div class="top-blog">
                    <div class="top-blog-list">
                        <p v-for="hotBlog in hotBlogs"><a :href="'/detail/'+hotBlog.id+'/id'">{{hotBlog.title}}</a></p>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
<div class="other">
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
    <p>ssssssssss</p>
</div>
    <%@include file="comm/footer.jsp" %>
</body>
</html>
