package cn.mrz.service;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BaseService<T> {
    public T getById(long id);
    T add(T t);
    void upd(T t);
    boolean del(T t);
}
