package com.example.community.community.dto;

import lombok.Data;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 05:04
 **/

/*
*
* */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
