package com.example.community.community.controller;


import com.example.community.community.dao.Question;
import com.example.community.community.dao.User;
import com.example.community.community.dto.PaginationDTO;
import com.example.community.community.dto.QuestionDTO;
import com.example.community.community.service.PublishService;
import com.example.community.community.service.QuestionService;
import com.example.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-03 10:13
 **/

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                        @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //通过 token 的查找,验证该用户信息是否在数据库中
                    User user = userService.findByToken(token);
                    if (user != null) {
                        //将 userInfo 命名为user 绑定到 Session
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        /*
        * 分页查询
        * */
        PaginationDTO paginationDTO = questionService.getList(pageNumber, pageSize);
        model.addAttribute("paginationDTO", paginationDTO);



        return "index";
    }


}
