package cn.mrz.dao.impl;

import cn.mrz.dao.AuthoritiesDao;
import cn.mrz.pojo.Authorities;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Repository
public class AuthoritiesDaoImpl extends BaseDaoImpl<Authorities> implements AuthoritiesDao {

    @Override
    public Authorities has(long id) {
        return null;
    }

    @Override
    public List<Authorities> getAuthoritiesByUsername(String username) {
        Session session = currentSession();
        List<Authorities> queryList = session.createQuery("from Authorities authorities where authorities.username=:username").setParameter("username", username).list();
        if(queryList.size()==0)
            return new ArrayList<>();
        else
            return queryList;

    }
}
