package com.example.community.community.controller;

import com.example.community.community.dao.Question;
import com.example.community.community.dao.User;
import com.example.community.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-10 04:09
 **/

@Controller
public class PublishController {


    @Autowired
    private PublishService publishService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model
    ) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);


        if (title == null || title == "") {
            model.addAttribute("error", "title 不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "description 不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "tag 不能为空");
            return "publish";
        }

        /*
         *  参照IndexController
         *  拿到user信息
         * */
        Question question = new Question();

        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreateTime(System.currentTimeMillis());
        question.setModifiedTime(question.getCreateTime());
        question.setCreatorId(user.getId());
        model.addAttribute("Question", question);
        publishService.addQuestion(question);

        return "redirect:/";
    }
}
