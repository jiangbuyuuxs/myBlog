package cn.mrz.dao.impl;

import cn.mrz.dao.UsersDao;
import cn.mrz.pojo.Blog;
import cn.mrz.pojo.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Repository
public class UsersDaoImpl extends BaseDaoImpl<User> implements UsersDao {

    @Override
    public User getUserByUsername(String username) {
        Session session = currentSession();
        List<User> list = session.createQuery("from User users where users.username=:username").setParameter("username", username).list();
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public List<User> getUsers(int start, int num, int sortBy) {
        Session session = currentSession();
        Query query = session.createQuery("from User order by username").setCacheable(true);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<User> list = query.list();
        return list;
    }

    @Override
    public User has(long id) {
        Session session = currentSession();
        List<User> list = session.createQuery("from User users where users.id=:id").setParameter("id", id).list();
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }
}
