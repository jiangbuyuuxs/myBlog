package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.dao.UsersDao;
import cn.mrz.pojo.User;
import cn.mrz.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service
public class UsersServiceImpl extends BaseServiceImpl<User> implements UsersService {

    @Autowired
    UsersDao usersDaoImpl;

    @Override
    public BaseDao<User> getBaseDao() {
        return usersDaoImpl;
    }

    @Override
    public boolean addUser(User user) {
        User userByUsername = usersDaoImpl.getUserByUsername(user.getUsername());
        if (userByUsername == null) {
            String password = user.getPassword();
            //不加盐
            String encodePassword = new Md5PasswordEncoder().encodePassword(password, null);
            user.setPassword(encodePassword);
            User newUser = usersDaoImpl.add(user);
            //TODO 添加权限
            if (newUser == null) {
                throw new RuntimeException("添加用户失败");
            }
            return true;
        } else {
            throw new RuntimeException("用户名已存在");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return usersDaoImpl.getUserByUsername(username);
    }

    @Override
    public List<User> getUsers(int start, int num, int sortBy) {
        return usersDaoImpl.getUsers(start, num, sortBy);
    }
}
