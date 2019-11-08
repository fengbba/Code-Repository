package com.example.community.community.controller;

import com.example.community.community.dto.AccessTokenDTO;
import com.example.community.community.dto.GithubUser;
import com.example.community.community.provider.GithubProvider;
import com.example.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 04:51
 **/


/*
 * Callback Controller
 * 需要对 accessToken 进行操作
 * */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${access_token_dto.client_id}")
    private String client_id;

    @Value("${access_token_dto.client_secret}")
    private String client_secret;

    @Value("${access_token_dto.redirect_uri}")
    private String redirect_uri;


    /*
     *  HttpServletRequest request,
     *  Spring会将上下文中的request存入到该形参中
     * */
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser userInfo = githubProvider.getUserInfo(accessToken);
        if (userInfo != null) {
            System.out.println("Star if");
            // 添加数据到数据库
            userService.insert(userInfo);

            // 登录成功, 编辑 Cookie & Session
            //将 userInfo 命名为user 绑定到 Session
            request.getSession().setAttribute("user", userInfo);

            // 重定向到根目录, 具体根据页面跳转url 修改
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }


    }

}
