package cn.mrz.dao;

import cn.mrz.pojo.User;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface UsersDao extends BaseDao<User> {
    User getUserByUsername(String username);
}
