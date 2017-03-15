package cn.mrz.controller;

import cn.mrz.pojo.Blog;
import cn.mrz.service.BlogsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
public class AdminController {

    @Resource
    private BlogsService blogsService;
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
    @ResponseBody
    @RequestMapping(value = "/admin/addblogs")
    public String addBlogs(Blog blog) {
        Date now = new Date(System.currentTimeMillis());
        blog.setCdate(now);
        blog.setEdate(now);
        if (blog.getTitle() == null || "".equals(blog.getTitle().trim()))
            blog.setTitle(new SimpleDateFormat("yyyy年MM月dd日HH时m分ss秒").format(now) + "写下的博客");
        blogsService.addBlog(blog);
        return "{\"success\": true}";
    }
}
