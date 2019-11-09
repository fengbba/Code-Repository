package com.example.community.community.controller;

import com.example.community.community.dao.User;
import com.example.community.community.dto.AccessTokenDTO;
import com.example.community.community.dto.GithubUser;
import com.example.community.community.provider.GithubProvider;
import com.example.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
                           HttpServletResponse response) {
        /*
         *  从配置文件中 丰富 accessTokenDTO
         * */
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        /*
         *   根据GitHub 授权登录文档,
         *   将 AccessTokenDTO 以 POST 请求的方式
         *   发送到 "https://github.com/login/oauth/access_token"
         *   以获取 access_token
         * */
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        /*
         *   根据GitHub 授权登录文档,
         *   将 access_token 以 GET 请求的方式
         *   发送到 "https://api.github.com/user"
         *   以获取 userInfo
         * */
        GithubUser userInfo = githubProvider.getUserInfo(accessToken);

        if (userInfo != null) {
            System.out.println("Star if");
            /*
             * 将 userInfo 存入数据库
             * */
            User user = userService.insert(userInfo);
            /*
             * 将 user 的 token 存入 Cookie 以 token 来命名
             * */
            response.addCookie(new Cookie("token", user.getToken()));


            // 重定向到根目录, 具体根据页面跳转url 修改
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }


    }

}
