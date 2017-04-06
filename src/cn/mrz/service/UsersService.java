package cn.mrz.service;

import cn.mrz.pojo.Blog;
import cn.mrz.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface UsersService extends BaseService<User> {
    boolean addUser(User user);
    User getUserByUsername(String username);
}
