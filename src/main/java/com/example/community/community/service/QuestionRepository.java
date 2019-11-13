package com.example.community.community.service;

import com.example.community.community.dao.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-13 04:48
 **/
public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
