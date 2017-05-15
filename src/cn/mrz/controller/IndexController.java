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
import java.util.ArrayList;
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
        List<Blog> blogs = blogsService.getBlogs(0, pageNum, null, false);
        int blogNums = blogsService.getBlogNums();
        List<Blog> hotBlogs = blogsService.getHotBlogs(5);
        List<Word> hotWords = wordService.getHotWords(0, 15, 0);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd mm:HH:ss"));
        String blogsJson;
        String hotBlogsJson;
        String hotWordsJson;
        try {
            blogsJson = mapper.writeValueAsString(blogs);
            hotBlogsJson = mapper.writeValueAsString(hotBlogs);
            hotWordsJson = mapper.writeValueAsString(hotWords);
        } catch (IOException e) {
            blogsJson = "{\"success\": false,\"msg\",\"获取信息失败\"}";
            hotBlogsJson = "{\"success\": false,\"msg\",\"获取热门博文失败\"}";
            hotWordsJson = "{\"success\": false,\"msg\",\"获取热词失败\"}";
        }
        map.addAttribute("blogs", blogsJson);
        map.addAttribute("blogNums", blogNums);
        map.addAttribute("hotBlogs", hotBlogsJson);
        map.addAttribute("hotWords", hotWordsJson);
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
        int start = (pageI - 1) * pageNum;
        List<Blog> blogs = blogsService.getBlogs(start, pageNum, null, false);
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

    @RequestMapping(value = {"/hotword/{hashcode}/id"})
    public String hotwords(ModelMap map,@PathVariable String hashcode) {
        List<Word> wordsByWordHash = wordService.getWordsByWordHash(hashcode);
        List<Blog> blogs = new ArrayList<Blog>();
        for(Word word:wordsByWordHash){
            blogs.add(blogsService.getById(word.getBlogid()));
        }
        map.addAttribute("blogs",blogs);
        return "/hotword";
    }

    @ResponseBody
    @RequestMapping(value = {"/visitblog/{blogid}"}, produces = "application/javascript")
    public String visitBlog(@PathVariable int blogid) {
        blogsService.addVisit(blogid);
        return "console.log(\"visit successed\");";
    }
}
