package com.example.community.community.controller;

import com.alibaba.fastjson.JSON;
import com.example.community.community.dao.Question;
import com.example.community.community.dao.User;
import com.example.community.community.service.PublishService;
import com.example.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
    @Autowired
    private UserService userService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model
    ) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);


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
        Cookie[] cookies = request.getCookies();
        Question question = new Question();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //通过 token 的查找,验证该用户信息是否在数据库中
                    User user = userService.findByToken(token);
                    if (user != null) {
                        question.setCreator_id(user.getId());
                    }
                    break;
                }
            }
        }
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreate_time(System.currentTimeMillis());
        question.setModified_time(question.getCreate_time());
        model.addAttribute("Question", question);
        publishService.addQuestion(question);

        return "redirect:/";
    }
}
