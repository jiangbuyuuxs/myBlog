package cn.mrz.dao;

import cn.mrz.pojo.User;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface UserDao extends BaseDao<User> {
    User getUserByUsername(String username);
}
