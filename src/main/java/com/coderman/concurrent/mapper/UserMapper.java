package com.coderman.concurrent.mapper;

import com.coderman.concurrent.model.User;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2021/2/7 11:48
 * @Version 1.0
 **/
public interface UserMapper {
    List<User> selectAll();

    User selectById(Integer id);
}
