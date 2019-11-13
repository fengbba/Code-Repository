package com.example.community.community.dto;

import com.example.community.community.dao.User;
import lombok.Data;

import java.util.Optional;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-13 04:24
 **/

@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Long create_time;
    private Long modified_time;
    private Integer creator_id;
    private Integer comment_count;
    private Integer like_count;
    private Integer view_count;
    private String tag;
    private User user;


}
