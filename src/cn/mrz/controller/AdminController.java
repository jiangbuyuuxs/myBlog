package cn.mrz.controller;

import cn.mrz.dao.VisitDao;
import cn.mrz.service.BlogsService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
public class AdminController {

    @Autowired
    private BlogsService blogsService;
    @Autowired
    private VisitDao visitDao;

    @RequestMapping(value = {"/admin/go/{page}"})
    public String goAdminPage(@PathVariable String page) {
        return "/admin/" + page;
    }

    @ResponseBody
    @RequestMapping(value = {"/admin/blogInfo"}, produces = {"application/json;charset=UTF-8"})
    public String getBlogInfo() {
        Map<String, Object> infos = new HashMap();
        int blogNums = blogsService.getBlogNums();
        int visitCount = visitDao.getVisitCount();
        infos.put("blogsCount", blogNums);
        infos.put("visitCount", visitCount);

        String infoJson = "{\"success\": false}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            infoJson = mapper.writeValueAsString(infos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infoJson;
    }


}
