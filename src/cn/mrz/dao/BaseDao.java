package cn.mrz.dao;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BaseDao<T> {
    T has(long id);
    int getCount();
    T add(T t);
    void upd(T t);
    void del(T t);
}
