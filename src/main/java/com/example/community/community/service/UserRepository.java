package com.example.community.community.service;

import com.example.community.community.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 21:01
 **/
@Component
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findById(Integer id);


    List<User> findByToken(String token);

    Optional<User> findByAccountId(long account_id);
}
