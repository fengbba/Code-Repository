package com.example.community.community.service;

import com.example.community.community.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 21:01
 **/
@Component
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByToken(String token);
}
