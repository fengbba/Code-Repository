package com.example.community.community.service;

import com.example.community.community.dao.User;
import com.example.community.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-07 20:51
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 添加数据
    public User insert(GithubUser githubUser) {
        User user = new User();
        user.setName(githubUser.getName());
        user.setAccount_id(String.valueOf(githubUser.getId()));
        user.setToken(UUID.randomUUID().toString());
        user.setCreate_time(System.currentTimeMillis());
        user.setModified_time(user.getCreate_time());
        user.setBio(githubUser.getBio());
        user.setAvatar_url(githubUser.getAvatar_url());
        userRepository.save(user);
        return user;
    }

    //判断 token 是否存在
    public User findByToken(String token) {
        List<User> users = userRepository.findByToken(token);
        if (users.size() > 0) {
            User user = users.get(0);
            return user;
        }
        return null;
    }
}
