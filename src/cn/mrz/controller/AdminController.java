package cn.mrz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
public class AdminController {

    @RequestMapping(value = {"/admin/go/{page}"})
    public String goAdminPage(@PathVariable String page) {
        return "/admin/" + page;
    }


}
