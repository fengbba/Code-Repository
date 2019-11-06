package com.example.community.community.controller;

import com.example.community.community.dto.AccessTokenDTO;
import com.example.community.community.dto.GithubUser;
import com.example.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 04:51
 **/

@Controller
/*
* Callback Controller
* 需要对 accessToken 进行操作
* */
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${access_token_dto.client_id}")
    private String client_id;
    @Value("${access_token_dto.client_secret}")
    private String client_secret;

    @Value("${access_token_dto.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser userInfo = githubProvider.getUserInfo(accessToken);
        System.out.println("userInfo : "+userInfo.getName());
//        登录成功,返回index页面
        return "index";
    }

}
