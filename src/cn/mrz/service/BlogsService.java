package cn.mrz.service;

import cn.mrz.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BlogsService extends BaseService<Blog> {
    List<Blog> getBlogs(int start, int num, String orderByNum, boolean hasContent);

    int getBlogNums();

    boolean addVisit(long blogidid);

    void addBlog(Blog blog);

    List<Blog> getHotBlogs(int blogNums);
}
