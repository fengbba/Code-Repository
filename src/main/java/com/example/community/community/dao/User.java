package com.example.community.community.dao;




import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 20:53
 **/
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long accountId;
    private String name;
    private String bio;
    private String token;
    private Long createTime;
    private Long modifiedTime;
    private String avatarUrl;


}
