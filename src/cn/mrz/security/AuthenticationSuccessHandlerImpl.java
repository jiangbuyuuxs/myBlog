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
        //        UserDetails user = (UserDetails) authentication.getPrincipal();
        //String username = user.getUsername();
        //获取认证信息
        //        Collection<? extends GrantedAuthority> auths = user.getAuthorities();
        //        String result = "";
        //        Object[] ats = auths.toArray();
        //        for (int i = 0; i < ats.length; i++) {
        //            if (((GrantedAuthority) ats[i]).getAuthority().toLowerCase().contains("admin")) {
        //                response.sendRedirect("/admin/admin");
        //                return;
        //            }
        //        }
        String returnUrl = request.getParameter("returnUrl");
        if(null==returnUrl||"".equals(returnUrl))
            returnUrl = "/";
        response.sendRedirect(returnUrl);
        //response.sendRedirect("/");
    }

}