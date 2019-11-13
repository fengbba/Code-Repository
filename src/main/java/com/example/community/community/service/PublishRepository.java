package com.example.community.community.service;

import com.example.community.community.dao.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-10 07:57
 **/
@Component
public interface PublishRepository extends JpaRepository<Question,Integer> {


}
