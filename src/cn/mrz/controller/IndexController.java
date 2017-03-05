package cn.mrz.controller;

import cn.mrz.exception.ParamException;
import cn.mrz.pojo.Blogs;
import cn.mrz.service.BlogsService;
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
import java.util.List;

@Controller
public class IndexController {

    private final int pageNum = 10;

    @Resource
    private BlogsService blogsService;

    @RequestMapping(value = {"/", "index", "home"})//
    public String goIndex(ModelMap map) {
        List<Blogs> blogs = blogsService.showBlogs(0, pageNum, null);
        int blogNums = blogsService.getBlogNums();
        map.addAttribute("blogs", blogs);
        map.addAttribute("blogNums", blogNums);
        return "/index";
    }

    @ResponseBody
    @RequestMapping(value = "/blog/{page}/page/{order}/order", produces = {"application/json;charset=UTF-8"})
    public String blogs(@PathVariable String page, @PathVariable String order) {
        int pageI = 0;
        try {
            pageI = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            return "{\"success\": false,\"msg\",\"页码非法\"}";
        }
        int start = (pageI - 1) * pageNum + 1;
        List<Blogs> blogs = blogsService.showBlogs(start, pageNum, order);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd mm:HH:ss"));
        String blogData = null;
        try {
            blogData = mapper.writeValueAsString(blogs);
        } catch (IOException e) {
            return "{\"success\": false,\"msg\",\"转换失败\"}";
        }
        return "{\"success\": true,\"data\":" + blogData + "}";
    }

    /**
     * /admin/*需要登录
     *
     * @return
     */
    @RequestMapping(value = {"/admin/admin"})
    public String goAdmin() {
        return "/admin";
    }

    @RequestMapping(value = {"/admin/add"})
    public String goNew(ModelMap map) {
        map.addAttribute("oper", "新增博文");
        return "/editpage";
    }

    @RequestMapping(value = "/detail/{id}/id")
    public String goDetail(@PathVariable String id, ModelMap map) {
        long idL = 0;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ParamException("博客编号非法", e);
        }
        Blogs blog = blogsService.getById(idL);
        //TODO 当前用户是否有权限查看该博文

        if (blog == null)
            return "redirect:/index";
        map.addAttribute("blog", blog);
        return "/detail";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/addblogs")
    public String addBlogs(Blogs blogs) {
        Date now = new Date(System.currentTimeMillis());
        blogs.setCdate(now);
        blogs.setEdate(now);
        if (blogs.getTitle() == null || "".equals(blogs.getTitle().trim()))
            blogs.setTitle(new SimpleDateFormat("yyyy年MM月dd日HH时m分ss秒").format(now) + "写下的博客");
        blogsService.add(blogs);
        return "{\"success\": true}";
    }

    @RequestMapping(value = {"/go/{pageName}"})
    public String goPage(@PathVariable String pageName) {
        return "/"+pageName;
    }

}
