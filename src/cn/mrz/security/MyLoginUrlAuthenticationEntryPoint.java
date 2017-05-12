package cn.mrz.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/31.
 */
public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    /**
     * 当未登录时,访问了需要登录才能访问的页面时,会执行到此处
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {


        String returnUrl = getUrlFromRequest(request);
        //TODO 这里需要判断一下这个无法访问的,到底是一个合法的路径还是一个数据接口.如果是一个数据接口,就这么放进去,登录之后一脸懵逼
        HttpSession session = request.getSession();
        session.setAttribute("returnUrl", returnUrl);
        super.commence(request, response, authException);
    }

    private String getUrlFromRequest(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
        try {
            url.append("http://");
            url.append(request.getServerName());
            url.append(":");
            url.append(request.getServerPort());
            String contextPath = request.getContextPath();
            contextPath = "".equals(contextPath) ? "" : contextPath + "/";
            url.append(contextPath);
            url.append(request.getServletPath());
            String pathInfo = request.getPathInfo();
            pathInfo = pathInfo == null ? "" : pathInfo;
            url.append(pathInfo);
            String queryString = request.getQueryString();
            queryString = queryString == null ? "" : queryString;
            url.append(queryString);
        }catch(Exception e){
            System.out.println("异常的页面跳转到登录页面");
            return "";
        }
        return url.toString();
    }
}
