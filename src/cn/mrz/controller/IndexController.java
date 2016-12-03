package cn.mrz.controller;

import cn.mrz.pojo.Blogs;
import cn.mrz.service.BlogsService;
import cn.mrz.service.impl.BlogsServiceImpl;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@Controller
public class IndexController {

    @Resource
    private BlogsService blogsService;

    @RequestMapping(value = {"/","index","home"})//
    public String goIndex(ModelMap map){
        List<Blogs> blogs = blogsService.showBlogs(0, 10, null);
        map.addAttribute("blogs",blogs);
        return "/index";
    }

    /**
     * /admin/*需要登录
     * @return
     */
    @RequestMapping(value = {"/admin/admin"})
    public String goAdmin(){
        return "/admin";
    }
    @RequestMapping(value = {"/admin/add"})
    public String goNew(ModelMap map){
        map.addAttribute("oper","新增博文");
        return "/editpage";
    }
    @RequestMapping(value = "/detail/{id}/id")
    public String goDetail(@PathVariable String id,ModelMap map){
        long idL = 0;
        try {
            idL = Long.parseLong(id);
        }catch(NumberFormatException e){
            String errMsg = "博客编号非法";
            return "redirect:/index";
        }
        Blogs blog = blogsService.getById(idL);
        if(blog==null)
            return "redirect:/index";
        map.addAttribute("blog",blog);
        return "/detail";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/addblogs")
    public String addBlogs(Blogs blogs){
        Date now = new Date();
        blogs.setCdate(now);
        blogs.setEdate(now);
        if(blogs.getTitle()==null||"".equals(blogs.getTitle()))
            blogs.setTitle(new SimpleDateFormat("yyyy年MM月dd日HH时m分ss秒").format(now)+"写下的博客");
        blogsService.add(blogs);
        return "{\"success\": true}";
    }

}
