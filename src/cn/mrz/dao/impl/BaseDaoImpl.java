package cn.mrz.dao.impl;

import cn.mrz.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2016/12/1.
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    /**
     * 由于使用spring的annotation注入时，HibernateDaoSupport不能注入sessionFactiry和hibernateTemplemet
     * @param sessionFactory
     */
    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    protected Class<T> entityClass;
    protected String className;

    public BaseDaoImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = entityClass.getName();
    }

    public T get(long id) {
        return (T) this.getHibernateTemplate().get(entityClass, id);
    }

    public void add(T t) {
        this.getHibernateTemplate().save(t);
    }

    public void del(T t) {
        this.getHibernateTemplate().delete(t);
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
