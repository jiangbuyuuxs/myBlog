package cn.mrz.dao.impl;

import cn.mrz.dao.VisitDao;
import cn.mrz.pojo.Visit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
@Repository
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

    @Override
    public Visit has(long id) {
        Session session = currentSession();
        List<Visit> list = session.createQuery("from Visit visit where visit.blogid=:id").setCacheable(true).setParameter("id", id).list();
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public List<Visit> getHotBlog(int num) {
        Session session = currentSession();
        Query query = session.createQuery("from Visit visit order by visit.num DESC").setCacheable(true);
        query.setFirstResult(0);
        query.setMaxResults(num);
        List<Visit> list = query.list();
        return list;
    }
}
