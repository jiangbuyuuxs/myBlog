package cn.mrz.controller;

import cn.mrz.pojo.Blog;
import cn.mrz.service.BlogsService;
import cn.mrz.service.WordService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Controller
public class BlogController {

    @Resource
    private BlogsService blogsService;
    @Resource
    private WordService wordService;

    @RequestMapping(value = {"/admin/blog/go/add"})
    public String goNewBlog(ModelMap map) {
        map.addAttribute("oper", "新增博文");
        return "/admin/blog/edit";
    }
    @RequestMapping(value = {"/admin/blog/{id}/edit"})
    public String goEditBlog(ModelMap map, @PathVariable long id) {
        Blog blog = blogsService.getById(id);
        map.addAttribute("oper", "修改博文");
        map.addAttribute("blog", blog);
        return "/admin/blog/edit";
    }

    @ResponseBody
    @RequestMapping(value = {"/admin/blog/{page}/page"}, produces = {"application/json;charset=UTF-8"})
    public String getBlogList(@PathVariable int page) {
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        List<Blog> blogs = blogsService.getBlogs(start, pageSize, null, false);
        int blogNums = blogsService.getBlogNums();

        String blogListJson = "{\"success\": false,\"msg\",\"获取失败\"}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd mm:HH:ss"));

        HashMap hashMap = new HashMap();
        hashMap.put("blogs", blogs);
        hashMap.put("blogNums", blogNums);
        try {
            blogListJson = mapper.writeValueAsString(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blogListJson;

    }

    @ResponseBody
    @RequestMapping(value = "/admin/blog/add")
    public String addBlogs(Blog blog) {
        Date now = new Date(System.currentTimeMillis());
        blog.setCdate(now);
        blog.setEdate(now);
        if (blog.getTitle() == null || "".equals(blog.getTitle().trim()))
            blog.setTitle(new SimpleDateFormat("yyyy年MM月dd日HH时m分ss秒").format(now) + "写下的博客");
        blogsService.addBlog(blog);
        final Blog blog2 = blog;
        new Thread(() -> wordService.getBlogWords(blog2)).start();
        return "{\"success\": true}";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/blog/edit")
    public String editBlogs(Blog blog) {
        if (null == blog) {
            return "{\"success\": false}";
        }
        Blog oldBlog = blogsService.getById(blog.getId());
        Date now = new Date(System.currentTimeMillis());
        blog.setEdate(now);
        blog.setClasstype(oldBlog.getClasstype());
        blog.setCdate(oldBlog.getCdate());
        blogsService.addBlog(blog);
        final Blog blog2 = blog;
        new Thread(() -> {
            wordService.getBlogWords(blog2);
        }).start();
        return "{\"success\": true}";
    }
}
