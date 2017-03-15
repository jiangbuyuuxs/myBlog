package cn.mrz.controller;

import cn.mrz.exception.ParamException;
import cn.mrz.pojo.Blog;
import cn.mrz.pojo.Word;
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
import java.util.List;

@Controller
public class IndexController {

    private final int pageNum = 10;

    @Resource
    private BlogsService blogsService;

    @Resource
    private WordService wordService;

    @RequestMapping(value = {"/", "index", "home"})
    public String goIndex(ModelMap map) {
        List<Blog> blogs = blogsService.getBlogs(0, pageNum, null, true);
        int blogNums = blogsService.getBlogNums();
        List<Blog> hotBlogs = blogsService.getHotBlogs(5);
        map.addAttribute("blogs", blogs);
        map.addAttribute("blogNums", blogNums);
        map.addAttribute("hotBlogs", hotBlogs);
        return "/index";
    }

    @RequestMapping(value = {"/go/{pageName}"})
    public String goPage(@PathVariable String pageName) {
        return "/" + pageName;
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
        List<Blog> blogs = blogsService.getBlogs(start, pageNum, order, false);
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

    @RequestMapping(value = "/detail/{id}/id")
    public String goDetail(@PathVariable String id, ModelMap map) {
        long idL = 0;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ParamException("博客编号非法", e);
        }
        Blog blog = blogsService.getById(idL);
        //TODO 当前用户是否有权限查看该博文

        if (blog == null)
            return "redirect:/go/error";
        map.addAttribute("blog", blog);
        return "/detail";
    }

    @RequestMapping(value = {"/hotwords"})
    public String hotwords(ModelMap map) {
        List<Word> hotwords = wordService.getHotwords(0, 20, 1);
        map.addAttribute("hotwords", hotwords);
        return "/hotwords";
    }

    @ResponseBody
    @RequestMapping(value = {"/chw"})
    public String hotwords() {
        int blogNums = blogsService.getBlogNums();
        List<Blog> blogs = blogsService.getBlogs(0, blogNums, null, true);
        wordService.saveWords(blogs);
        return "zhizhizhi";
    }

    @ResponseBody
    @RequestMapping(value = {"/visitblog/{blogid}"}, produces = "application/javascript")
    public String visitBlog(@PathVariable int blogid) {
        blogsService.addVisit(blogid);
        return "console.log(\"visit successed\");";
    }
}
