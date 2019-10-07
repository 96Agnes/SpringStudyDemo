package com.example.test.springtest.service;

import com.example.test.springtest.mapper.UserMapper;
import com.example.test.springtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void CreateOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
        else{
            //更新
            user.setGmtModified(user.getGmtCreate());
            userMapper.update(user);
        }

    }
}
