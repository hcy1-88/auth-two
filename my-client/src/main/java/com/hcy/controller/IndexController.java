package com.hcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 16:59
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }
}
