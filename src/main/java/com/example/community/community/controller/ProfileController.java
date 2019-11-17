package com.example.community.community.controller;

import com.example.community.community.dao.User;
import com.example.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-16 14:54
 **/
@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("question".equals(action)) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的问题");
        }

        if ("replise".equals(action)) {
            model.addAttribute("section", "replise");
            model.addAttribute("sectionName", "最新回复");
        }


        return "profile";
    }
}
