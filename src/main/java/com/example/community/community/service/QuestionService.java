package com.example.community.community.service;

import com.example.community.community.dao.Question;
import com.example.community.community.dao.User;
import com.example.community.community.dto.PaginationDTO;
import com.example.community.community.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


    public PaginationDTO getList(Integer pageNumber, Integer pageSize) {
        /*
         * 创建时间降序排序
         * */
        //Sort sort = new Sort(Sort.Direction.DESC,"create_time");

        /*
         * 创建分页
         * */
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Question> pageList = questionRepository.findAll(pageable);

        List<Question> questionList = pageList.getContent();

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {

            Optional<User> user = userRepository.findById(question.getCreator_id());
            QuestionDTO questionDTO = new QuestionDTO();

            questionDTO.setQuestion(question);
            questionDTO.setUser(user.get());
            questionDTOList.add(questionDTO);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestionDTOList(questionDTOList);
        paginationDTO.setPaination(pageList.getTotalElements(), pageNumber, pageSize);


        return paginationDTO;
    }


}
