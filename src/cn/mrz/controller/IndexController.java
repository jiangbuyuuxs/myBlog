package cn.mrz.controller;

import cn.mrz.pojo.Blogs;
import cn.mrz.service.BlogsService;
import cn.mrz.service.impl.BlogsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

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
    @RequestMapping(value = {"/admin"})
    public String goAdmin(){
        return "/admin";
    }
    @RequestMapping(value = {"/add"})
    public String goNew(ModelMap map){
        map.addAttribute("oper","新增博文");
        return "/editpage";
    }
    @RequestMapping(value = "/detail/{id}/id")
    public String goDetail(@PathVariable String id,ModelMap map){
        Blogs blog = blogsService.getById(Long.parseLong(id));
        if(blog==null)
            return "/index";
        map.addAttribute("blog",blog);
        return "/detail";
    }

}
