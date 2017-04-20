package cn.mrz.dao.impl;

import cn.mrz.dao.CyDao;
import cn.mrz.pojo.Cy;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */
@Repository
public class CyDaoImpl extends BaseDaoImpl<Cy> implements CyDao {
    @Override
    public Cy has(long id) {
        Session session = currentSession();
        List<Cy> list = session.createQuery("from Cy cy where cy.id=:id").setCacheable(true).setParameter("id", id).list();
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public List<Cy> getCyByPyfirst(String pyFirst) {
        Session session = currentSession();
        return session.createQuery("from Cy cy where cy.pyfirst=:pyfirst").setCacheable(true).setParameter("pyfirst", pyFirst).list();
    }
    @Override
    public List<Cy> getCyByPyend(String pyEnd) {
            Session session = currentSession();
            return session.createQuery("from Cy cy where cy.pyend=:pyend").setCacheable(true).setParameter("pyend", pyEnd).list();
    }

    @Override
    public Cy getCy(String cy) {
        Session session = currentSession();
        List<Cy> list = session.createQuery("from Cy cy where cy.cy=:cy").setCacheable(true).setParameter("cy", cy).list();
        if(list.size()==0)
            return null;
        else
            return list.get(0);    }
}
