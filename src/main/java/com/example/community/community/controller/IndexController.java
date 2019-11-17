package com.example.community.community.controller;


import com.example.community.community.dao.User;
import com.example.community.community.dto.PaginationDTO;
import com.example.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-03 10:13
 **/

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                        @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {



        /*
         * 分页查询
         * */
        PaginationDTO paginationDTO = questionService.getList(pageNumber, pageSize);
        model.addAttribute("paginationDTO", paginationDTO);


        return "index";
    }


}
