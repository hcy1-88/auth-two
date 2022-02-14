package com.hcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 20:31
 */
@Controller
public class PageController {
    @RequestMapping("/confuse")
    @ResponseBody
    public String confuse(){
        return "登录失败！";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "authenticate";
    }

    @RequestMapping("/toAuthorize")
    public String toAuthorize() {
        return "oauth";
    }

}
