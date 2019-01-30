package com.xforce.cn.sportview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
     return "index";
    }

    @RequestMapping("/jsonp")
    @ResponseBody
    public String jsonp (){
        String callback="getData";
        return callback + "({'BookName':'English','Pages':562})";
    }

}
