package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.dao.BlogsDao;
import cn.mrz.pojo.Blogs;
import cn.mrz.service.BlogsService;
import cn.mrz.task.Hotwords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
public class BlogsServiceImpl extends BaseServiceImpl<Blogs> implements BlogsService{
    @Autowired
    private BlogsDao blogsDaoImpl;

    /**
     * 向接口传递DaoImpl
     * @return
     */
    @Override
    public BaseDao<Blogs> getBaseDao() {
            return blogsDaoImpl;
    }

    /**
     * 获取文章列表信息
     * @param start 开始篇数
     * @param num 总篇数
     * @return 博文信息,不包含
     */
    public List<Blogs> showBlogs(int start,int num,String orderByNum){
        if(orderByNum==null)
            orderByNum = "0";
        int integer = Integer.valueOf(orderByNum, 2);
        return blogsDaoImpl.getBlogsWithoutContent(start, num, integer);
    }

    @Override
    public int getBlogNums() {
        return blogsDaoImpl.getCount();
    }

    @Override
    public Map<String, Integer> getHotwords() {
        int blogNums = getBlogNums();
        List<Blogs> blogs = showBlogs(0, blogNums, null);
        Hotwords hotwords = new Hotwords();
        for (Blogs blog:blogs){
            hotwords.addText(blog.getTexts());
            hotwords.addText(blog.getTitle());
        }
        return hotwords.getHotwards();
    }

}
