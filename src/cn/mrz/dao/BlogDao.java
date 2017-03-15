package cn.mrz.dao;

import cn.mrz.pojo.Blog;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BlogDao extends BaseDao<Blog> {
    List<Blog> getBlogs(int start,int num,int sortBy);
    List<Blog> getBlogsWithoutContent(int start,int num,int sortBy);
}
