package cn.mrz.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/5.
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request,
                                       HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //TODO 根据登录用户权限不同,重定向到不同的页面
        response.sendRedirect(request.getContextPath());
    }

}