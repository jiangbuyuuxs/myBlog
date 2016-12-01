package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.service.BaseService;

/**
 * Created by Administrator on 2016/12/1.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 无法注入BaseDao
     * @return
     */
    public abstract BaseDao<T> getBaseDao();

    public T getById(long id) {
        return getBaseDao().has(id);
    }

    public void add(T t) {
        getBaseDao().add(t);
    }

    public void upd(T t) {
        getBaseDao().upd(t);
    }

    public void del(T t) {
        getBaseDao().del(t);
    }
}