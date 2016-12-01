package cn.mrz.service;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BaseService<T> {
    public T getById(long id);
    void add(T t);
    void upd(T t);
    void del(T t);
}
