package cn.mrz.dao.impl;

import cn.mrz.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2016/12/1.
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    /**
     * 由于使用spring的annotation注入时，HibernateDaoSupport不能注入sessionFactiry和hibernateTemplemet
     *
     * @param sessionFactory
     */
    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    protected Class<T> entityClass;
    protected String className;

    public BaseDaoImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = entityClass.getName();
    }

    public T get(long id) {
        //TODO get越过二级缓存
        return (T) this.getHibernateTemplate().get(entityClass, id);
        //return (T) this.getHibernateTemplate().load(entityClass, id);
    }

    public T add(T t) {
        this.getHibernateTemplate().save(t);
        return t;
    }

    public boolean del(T t) {
        try {
            this.getHibernateTemplate().delete(t);
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void upd(T t) {
        this.getHibernateTemplate().update(t);
    }

    public int getCount() {
        Object obj = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createQuery("select count(1) from " + className).uniqueResult();
            }
        });
        return (int) ((Long) obj).longValue();
    }

}
