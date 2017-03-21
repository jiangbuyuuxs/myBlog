package cn.mrz.controller;

import cn.mrz.pojo.Blog;
import cn.mrz.service.BlogsService;
import cn.mrz.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
public class AdminController {

    @Resource
    private BlogsService blogsService;
    @Resource
    private WordService wordService;
    /**
     * /admin/*需要登录
     *
     * @return
     */
    @RequestMapping(value = {"/admin/admin"})
    public String goAdmin() {
        return "/admin/admin";
    }

    @RequestMapping(value = {"/admin/add"})
    public String goNewBlog(ModelMap map) {
        map.addAttribute("oper", "新增博文");
        return "/admin/editpage";
    }

    @RequestMapping(value = {"/admin/{id}/edit"})
    public String goEditBlog(ModelMap map,@PathVariable long id) {
        Blog blog = blogsService.getById(id);
        map.addAttribute("oper", "修改博文");
        map.addAttribute("blog", blog);
        return "/admin/editpage";
    }
    @ResponseBody
    @RequestMapping(value = "/admin/addblog")
    public String addBlogs(Blog blog) {
        Date now = new Date(System.currentTimeMillis());
        blog.setCdate(now);
        blog.setEdate(now);
        if (blog.getTitle() == null || "".equals(blog.getTitle().trim()))
            blog.setTitle(new SimpleDateFormat("yyyy年MM月dd日HH时m分ss秒").format(now) + "写下的博客");
        blogsService.addBlog(blog);
        final Blog blog2 = blog;
        new Thread(new Runnable() {
            @Override
            public void run() {
                wordService.getBlogWords(blog2);
            }
        }).run();
        return "{\"success\": true}";
    }
    @ResponseBody
    @RequestMapping(value = "/admin/editblog")
    public String editBlogs(Blog blog) {
        Blog oldBlog = blogsService.getById(blog.getId());
        Date now = new Date(System.currentTimeMillis());
        blog.setEdate(now);
        blog.setClasstype(oldBlog.getClasstype());
        blog.setCdate(oldBlog.getCdate());
        blogsService.addBlog(blog);
        final Blog blog2 = blog;
        new Thread(new Runnable() {
            @Override
            public void run() {
                wordService.getBlogWords(blog2);
            }
        }).run();
        return "{\"success\": true}";
    }
}
