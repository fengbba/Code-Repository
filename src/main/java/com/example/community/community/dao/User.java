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
    private String account_id;
    private String name;
    private String bio;
    private String token;
    private Long create_time;
    private Long modified_time;
    private String avatar_url;


}
