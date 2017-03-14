package cn.mrz.service;

import cn.mrz.pojo.Blogs;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface BlogsService extends BaseService<Blogs> {
    List<Blogs> showBlogs(int start,int num,String orderByNum);
    int getBlogNums();
    Map<String,Integer> getHotwords();
}
