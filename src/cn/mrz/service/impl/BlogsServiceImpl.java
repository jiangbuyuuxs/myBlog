package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.dao.BlogDao;
import cn.mrz.dao.VisitDao;
import cn.mrz.pojo.Blog;
import cn.mrz.pojo.Visit;
import cn.mrz.service.BlogsService;
import cn.mrz.task.Hotwords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
public class BlogsServiceImpl extends BaseServiceImpl<Blog> implements BlogsService {
    @Autowired
    private BlogDao blogDaoImpl;

    @Autowired
    private VisitDao visitDaoImpl;

    /**
     * 向接口传递DaoImpl
     *
     * @return
     */
    @Override
    public BaseDao<Blog> getBaseDao() {
        return blogDaoImpl;
    }

    /**
     * 获取文章列表信息
     *
     * @param start 开始篇数
     * @param num   总篇数
     * @return 博文信息, 不包含
     */
    public List<Blog> getBlogs(int start, int num, String orderByNum, boolean hasContent) {
        if (orderByNum == null)
            orderByNum = "0";
        int integer = Integer.valueOf(orderByNum, 2);
        if (!hasContent)
            return blogDaoImpl.getBlogsWithoutContent(start, num, integer);
        else
            return blogDaoImpl.getBlogs(start, num, integer);
    }

    @Override
    public int getBlogNums() {
        return blogDaoImpl.getCount();
    }

    @Override
    public boolean addVisit(long blogid) {
        try {
            Visit visit = visitDaoImpl.has(blogid);
            if (null == visit) {
                visit = new Visit();
                visit.setBlogid(blogid);
                visit.setNum(1);
            } else {
                visit.setNum(visit.getNum() + 1);
            }
            visitDaoImpl.add(visit);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Override
    public void addBlog(Blog blog) {
        Blog add = blogDaoImpl.add(blog);
        Visit visit = new Visit();
        visit.setBlogid(add.getId());
        visit.setNum(0);
        visitDaoImpl.add(visit);
    }

    @Override
    public List<Blog> getHotBlogs(int blogNums) {
        List<Visit> hotBlogs = visitDaoImpl.getHotBlog(blogNums);
        ArrayList<Blog> blogs = new ArrayList<Blog>();
        for (Visit hotBlog : hotBlogs) {
            long blogid = hotBlog.getBlogid();
            Blog hotblogs = blogDaoImpl.has(blogid);
            blogs.add(new Blog(hotblogs.getId(),hotblogs.getTitle(), hotblogs.getCdate(), hotblogs.getEdate(),hotblogs.getImgid(),hotblogs.getClasstype()));
        }
        return blogs;
    }


}
