package com.example.community.community.service;

import com.example.community.community.dao.User;
import com.example.community.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public User createOfUpdate(GithubUser githubUser) {
        Optional<User> byAccountId = userRepository.findByAccountId(githubUser.getId());
        User user = new User();
        if (byAccountId.isPresent()) {          // Optional.empty
            System.out.println("update");
            //更新
            user = byAccountId.get();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setModifiedTime(System.currentTimeMillis());
        } else {
            System.out.println("insert");
            //插入
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
        }
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
