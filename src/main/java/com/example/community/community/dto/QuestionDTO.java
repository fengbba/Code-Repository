package com.example.community.community.dto;

import com.example.community.community.dao.Question;
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

    private Question question;
    private User user;

}
