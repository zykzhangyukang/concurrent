package com.coderman.concurrent.service.impl;

import com.coderman.concurrent.mapper.UserMapper;
import com.coderman.concurrent.model.User;
import com.coderman.concurrent.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:24
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }
}
