package cn.mrz.controller;

import cn.mrz.pojo.User;
import cn.mrz.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/6.
 */
@Controller
public class UserController {

    @Resource
    private UsersService usersService;

    @RequestMapping(value = {"/admin/user/add"})
    public String addUser(User user) {
        boolean add = usersService.addUser(user);
        if (add) {
            return "/admin/user/go/manager";
        }
        return "/admin/user/go/add";
    }

    @RequestMapping(value = {"/admin/user/{username}/info"})
    public String userInfo(ModelMap map,@PathVariable String username) {
        User user = usersService.getUserByUsername(username);
        if(user==null){
            throw new RuntimeException("查无此人!");
        }
        map.addAttribute("user", user);
        return "/admin/user/info";
    }

    @RequestMapping(value = {"/admin/user/go/{page}"})
    public String goUserPage(@PathVariable String page) {
        return "/admin/user/" + page;
    }
}
