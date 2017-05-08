package cn.mrz.dao;

import cn.mrz.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface UsersDao extends BaseDao<User> {
    User getUserByUsername(String username);
    List<User> getUsers(int start,int num,int sortBy);
}
