package com.hcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 21:41
 */
@Controller
public class UserController {
    @RequestMapping("/ok")
    public String ok() {
        return "success";
    }
}
