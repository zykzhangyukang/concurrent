package com.coderman.concurrent.service;

import com.coderman.concurrent.model.User;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:23
 * @Version 1.0
 **/
public interface IUserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return
     */
    User findById(Integer id);
}
