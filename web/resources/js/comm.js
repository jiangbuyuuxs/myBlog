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
     * TODO 登录失效时,使用ajax方式登录
     * @param data
     */
    function checkLogin(data){
        var contentType = data.headers.map["Content-Type"][0];
        if(contentType && contentType.indexOf("html")!=-1){
            alert("登录超时,请重新登录");
            window.location.href = "/";
        }
    }

    window['BlogTool']['checkLogin'] = checkLogin;

})($);
