package cn.mrz.controller;

import cn.mrz.pojo.User;
import cn.mrz.service.UsersService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Controller
public class UserController {

    @Resource
    private UsersService usersService;

    @ResponseBody
    @RequestMapping(value = "/admin/user/{page}/page", produces = {"application/json;charset=UTF-8"})
    public String getUsersinfo(@PathVariable int page) {
        int pageSize = 10;
        int start = (page - 1) * 10;
        List<User> users = usersService.getUsers(start, pageSize, 0);
        ObjectMapper mapper = new ObjectMapper();
        String usersJson = null;
        try {
            usersJson = mapper.writeValueAsString(users);
        } catch (IOException e) {
            e.printStackTrace();
            usersJson = "{\"success\": false}";
        }
        return usersJson;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/user/{username}/del/{page}/page", produces = {"application/json;charset=UTF-8"})
    public String getUsersinfo(@PathVariable String username, @PathVariable int page) {
        String usersJson = null;
        User delUser = usersService.getUserByUsername(username);
        if (delUser == null) {
            usersJson = "{\"success\": false,\"msg\":\"未找到需要删除的用户\"}";
            return usersJson;
        }
        boolean delNum = usersService.del(delUser);
        if (!delNum) {
            usersJson = "{\"success\": false,\"msg\":\"未成功删除用户\"}";
            return usersJson;
        }
        int pageSize = 10;
        int start = (page - 1) * 10;
        int end = start + pageSize;
        List<User> users = usersService.getUsers(start, end, 0);
        ObjectMapper mapper = new ObjectMapper();
        try {
            usersJson = mapper.writeValueAsString(users);
        } catch (IOException e) {
            e.printStackTrace();
            usersJson = "{\"success\": false}";
        }
        return usersJson;
    }
}
