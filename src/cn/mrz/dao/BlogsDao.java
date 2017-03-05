package cn.mrz.dao;

import cn.mrz.dao.impl.BaseDaoImpl;
import cn.mrz.pojo.Blogs;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BlogsDao extends BaseDao<Blogs> {
    public abstract List<Blogs> getBlogs(int start,int num,int sortBy);
    public abstract List<Blogs> getBlogsWithoutContent(int start,int num,int sortBy);

}
