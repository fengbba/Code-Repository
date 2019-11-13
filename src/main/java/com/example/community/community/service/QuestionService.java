package com.example.community.community.service;

import com.alibaba.fastjson.JSON;
import com.example.community.community.dao.Question;
import com.example.community.community.dao.User;
import com.example.community.community.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-13 04:49
 **/
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;


    public List<QuestionDTO> getList() {
        List<Question> questionList = questionRepository.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            Optional<User> user = userRepository.findById(question.getCreator_id());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user.get());
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;

    }
}
