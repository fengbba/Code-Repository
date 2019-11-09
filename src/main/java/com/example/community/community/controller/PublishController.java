package com.example.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-10 04:09
 **/

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
