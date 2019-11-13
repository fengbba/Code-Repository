package com.example.community.community.service;

import com.alibaba.fastjson.JSON;
import com.example.community.community.dao.Question;
import com.example.community.community.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-10 07:59
 **/
@Service
public class PublishService {
    @Autowired
    private PublishRepository publishRepository;

    /*
     * 添加问题
     * */
    public void addQuestion(Question question) {
        publishRepository.save(question);
    }





}
