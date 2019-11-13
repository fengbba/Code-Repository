package com.example.community.community.dto;

import lombok.Data;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 06:23
 **/

@Data
public class GithubUser {

    private String name;
    private long id;
    private String bio;
    private String avatar_url;


}
