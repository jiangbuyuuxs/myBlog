package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/12/1.
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 无法注入BaseDao
     * @return
     */
    public abstract BaseDao<T> getBaseDao();

    public T getById(long id) {
        return getBaseDao().has(id);
    }

    public T add(T t) {
        return getBaseDao().add(t);
    }

    public void upd(T t) {
        getBaseDao().upd(t);
    }

    public boolean del(T t) {
        return getBaseDao().del(t);
    }
}