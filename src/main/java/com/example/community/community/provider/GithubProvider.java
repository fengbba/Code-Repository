package com.example.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.example.community.community.dto.AccessTokenDTO;
import com.example.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 05:03
 **/

@Component

public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        //设置前台返回信息以 json 格式 返回
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            return string;
        } catch (Exception e) {
        }
        return "";
    }

    public GithubUser getUserInfo(String accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+accessTokenDTO)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
