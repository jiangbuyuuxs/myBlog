package cn.mrz.security;

import cn.mrz.dao.AuthoritiesDao;
import cn.mrz.dao.UserDao;
import cn.mrz.pojo.Authorities;
import cn.mrz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserDao userDao;
    @Autowired
    public AuthoritiesDao authoritiesDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userDao.getUserByUsername(username);
        Collection<GrantedAuthority> authList = getAuthorities(username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, userByUsername.getPassword(), true, true, true, true,
                authList);
        return userDetails;
    }

    private Collection<GrantedAuthority> getAuthorities(String username) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        //从数据库获取权限
        List<Authorities> authoritiesByUsername = authoritiesDao.getAuthoritiesByUsername(username);
        for (Authorities authoritie : authoritiesByUsername) {
            authList.add(new SimpleGrantedAuthority(authoritie.getAuthority()));
        }
        return authList;
    }
}
