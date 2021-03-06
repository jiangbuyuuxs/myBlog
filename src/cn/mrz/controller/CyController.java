package cn.mrz.controller;

import cn.mrz.dao.CyDao;
import cn.mrz.pojo.Cy;
import cn.mrz.pojo.User;
import cn.mrz.service.UsersService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/6.
 */
@Controller
public class CyController {

    @Resource
    private CyDao cyDao;

    @RequestMapping(value = {"/cy/cyjl"})
    public String goCyPage() {
        return "/cy/cy";
    }

    @ResponseBody
    @RequestMapping(value = {"/cy/cy/cy"}, produces = {"application/json;charset=UTF-8"})
    public String getCy(@RequestParam(required = false) String cy) {
        if(cy==null){
            cy = "自力更生";
        }
        //TODO 这里是可以按照参数输入来减少一步sql查询的.
        String json = "{\"success\":false}";
        if (cy != null && cy.trim().length() > 2) {
            Cy chenyu = cyDao.getCy(cy);
            if (chenyu != null) {
                String pyend = chenyu.getPyend();
                List<Cy> cyByPyfirst = cyDao.getCyByPyfirst(pyend);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = new HashMap();
                map.put("success", true);
                map.put("cy", chenyu);
                map.put("num", 0);
                if (cyByPyfirst != null && cyByPyfirst.size() > 0) {
                    map.put("num", cyByPyfirst.size());
                    map.put("nextcy", cyByPyfirst);
                }
                try {
                    json = mapper.writeValueAsString(map);
                } catch (IOException e) {
                    e.printStackTrace();
                    return json;
                }
            }
        }
        return json;
    }

}
