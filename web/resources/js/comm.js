/**
 *
 * Created by Administrator on 2017/5/9.
 */


(function ($) {
    if (!window.BlogTool) {
        window['BlogTool'] = {}
    }
    /**
     *检查ajax请求时,是否已登录
     *
     * @param data
     */
    function checkLogin(data){
        var contentType = data.headers.map["Content-Type"][0];
        if(contentType && contentType.indexOf("html")!=-1){
            alert1("登录超时,请重新登录");
            //TODO 登录失效时,使用ajax方式登录
            goUrl("/go/logon");
        }
    }

    window['BlogTool']['checkLogin'] = checkLogin;

    function logout(){
        var csrfData = addCSRFToken();
        $.ajax("/logout", {
            type:"POST",
            data:csrfData,
            success: function (data) {
                if(data.success){
                    goUrl();
                }
            },
            error:function () {
                alert1("退出失败");
            }
        });
    }

    window['BlogTool']['logout'] = logout;

    function goUrl(url){
        url = url || "/";
        window.location.href = url;
    }

    function alert1(msg,opt){
        alert(msg);
    }
    function addCSRFToken(data){
        var data = data||{};
        var val = $("#csrfParam").val();
        var name = $("#csrfParam").attr("name");
        data[name] = val;
        return data;
    }
    window['BlogTool']['addCSRFToken'] = addCSRFToken;


})($);
