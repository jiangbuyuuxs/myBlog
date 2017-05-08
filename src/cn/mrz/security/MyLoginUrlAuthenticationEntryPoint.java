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
     * 当访问了需要登录才能访问的页面时,会执行到此处
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String returnUrl = getUrlFromRequest(request);
        HttpSession session = request.getSession();
        //session.removeAttribute("returnUrl");
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
