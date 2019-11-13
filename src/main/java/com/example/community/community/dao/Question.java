package com.example.community.community.dao;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-10 07:50
 **/
@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Long create_time;
    private Long modified_time;
    private Integer creator_id;
    private String tag;
    /*
    * @Column(name = "XXX" , insertable = false)
    * 表示在使用“INSERT”脚本插入数据时，是否需要插入该字段的值。
    * false 时, 主要用来搭配默认值&不需要修改的值
    * */
    @Column(name = "comment_count",insertable = false)
    private Integer comment_count;
    @Column(name = "like_count",insertable = false)
    private Integer like_count;
    @Column(name = "view_count",insertable = false)
    private Integer view_count;



}
